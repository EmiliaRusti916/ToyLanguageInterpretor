package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import model.HeapInterface;
import model.MapInterface;
import program.ProgramState;
import type.RefType;
import type.TypeInterface;
import value.RefValue;
import value.ValueInterface;

import java.io.IOException;

public class HeapWritingStmt implements StatementInterface{
    private final String heapAddress;
    private final ExpInterface exp;

    public HeapWritingStmt(String heapAddr, ExpInterface exp) {
        this.heapAddress = heapAddr;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        MapInterface<String, ValueInterface> symTbl = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        if(!symTbl.isDefined(this.heapAddress))
            throw new MyException("Variable named " + this.heapAddress + " not defined!");
        ValueInterface varValue = symTbl.getValueByKey(this.heapAddress);
        TypeInterface type = varValue.getType();
        if(!(type instanceof RefType))
            throw new MyException("Invalid type in writing!");
        RefValue refValue = (RefValue) varValue;
        if(!heap.isDefined(refValue.getAddress()))
            throw new MyException("Address" + refValue.getAddress() + "is not in the heap!");
        try{
            ValueInterface expValue = this.exp.evaluate(symTbl, heap);
            if(!expValue.getType().equals(refValue.getLocationType()))
                throw new MyException("Location type and variable type do not match!");
            heap.update(refValue.getAddress(), expValue);
        }catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "HeapWritingStatement(" +
                "heapAddress='" + heapAddress + '\'' +
                ", exp=" + exp +
                ')';
    }

    @Override
    public StatementInterface deepCopy() {
        return new HeapWritingStmt(this.heapAddress, this.exp.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface type = typeEnvironment.getValueByKey(this.heapAddress);
        TypeInterface expressionType = this.exp.typeCheck(typeEnvironment);
        if (!type.equals(new RefType(expressionType))) {
            throw new MyException("Variable is not a reference type in HeapWritingStmt.");
        }
        return typeEnvironment;
    }
}
