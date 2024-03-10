package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import model.HeapInterface;
import model.ListInterface;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.TypeInterface;
import value.ValueInterface;

public class PrintStmt implements StatementInterface{
    ExpInterface exp;

    public PrintStmt(ExpInterface exp) {
        this.exp = exp;
    }

    public String toString(){
        return "print"+this.exp.toString();
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> stk = state.getStack();
        MapInterface<String, ValueInterface> symTbl = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        ListInterface<ValueInterface> list = state.getOut();
        try{
            ValueInterface val = exp.evaluate(symTbl, heap);
            list.add(val);
        }catch (MyException e){
            throw new MyException(e.toString());
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        this.exp.typeCheck(typeEnvironment);
        return typeEnvironment;
    }
}
