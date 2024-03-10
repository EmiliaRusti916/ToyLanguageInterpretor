package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import model.FileTableInterface;
import model.HeapInterface;
import model.MapInterface;
import program.ProgramState;
import type.StringType;
import type.TypeInterface;
import value.StringValue;
import value.ValueInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStatement implements StatementInterface{
    ExpInterface exp;

    public OpenRFileStatement(ExpInterface exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "OpenRFileStatement(" +
                exp +
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MapInterface<String, ValueInterface> symTbl = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface val = exp.evaluate(symTbl, heap);
        if (!val.getType().equals(new StringType()))
            throw new MyException("Not of type String!");
        FileTableInterface<String, BufferedReader> fileTbl = state.getFileTbl();
        StringValue val2 = (StringValue) val;
        if(fileTbl.isDefined(val2.getVal()))
            throw new MyException("File already opened!");
        try {
            BufferedReader buff = new BufferedReader(new FileReader(val2.getVal()));
            fileTbl.add(val2.getVal(), buff);
        } catch (FileNotFoundException e) {
            throw new MyException(e.getMessage());
        }

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new OpenRFileStatement(exp.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
            TypeInterface expressionType = this.exp.typeCheck(typeEnvironment);
            if (!expressionType.equals(new StringType())) {
                throw new MyException("Expression is not of type string in OpenRFileStatement.");
            }
        return typeEnvironment;
    }
}
