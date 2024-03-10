package statement;

import com.sun.jdi.Value;
import exceptions.MyException;
import expressions.ExpInterface;
import model.FileTableInterface;
import model.HeapInterface;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.StringType;
import type.TypeInterface;
import value.StringValue;
import value.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements StatementInterface{
    ExpInterface exp;

    @Override
    public String toString() {
        return "CloseRFileStatement{" +
                "exp=" + exp +
                '}';
    }

    public CloseRFileStatement(ExpInterface exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        StackInterface<StatementInterface> stack = state.getStack();
        MapInterface<String, ValueInterface> symTbl = state.getMap();
        FileTableInterface<String,BufferedReader> fileTbl = state.getFileTbl();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface val = exp.evaluate(symTbl, heap);
        if(!val.getType().equals(new StringType()))
            throw new MyException("Exp not of type String!");
        StringValue val2 = (StringValue) val;
        if(!fileTbl.isDefined(val2.getVal()))
            throw new MyException("Value not defined!");
        BufferedReader buff = fileTbl.getValueByKey(val2.getVal());
        buff.close();
        fileTbl.removeByKey(val2.getVal());
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new CloseRFileStatement(exp.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface expressionType = this.exp.typeCheck(typeEnvironment);
        if (!expressionType.equals(new StringType()))
            throw new MyException("Expression in CloseRFileStatement is not of type string.");
        return typeEnvironment;
    }
}
