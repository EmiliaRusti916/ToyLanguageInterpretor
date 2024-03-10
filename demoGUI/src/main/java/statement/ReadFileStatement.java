package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import model.FileTableInterface;
import model.HeapInterface;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.IntType;
import type.StringType;
import type.TypeInterface;
import value.IntValue;
import value.StringValue;
import value.ValueInterface;

import java.io.*;

public class ReadFileStatement implements  StatementInterface{
    ExpInterface exp;
    String variable;

    @Override
    public String toString() {
        return "ReadFileStatement{" +
                "exp=" + exp +
                ", variable='" + variable + '\'' +
                '}';
    }

    public ReadFileStatement(ExpInterface epx, String s) {
        this.exp = epx;
        this.variable = s;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        StackInterface<StatementInterface> stack = state.getStack();
        MapInterface<String, ValueInterface> symTbl = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        FileTableInterface<String,BufferedReader> fileTbl = state.getFileTbl();
        if(!symTbl.isDefined(variable))
        {
            throw new MyException("Variable is not defined!");
        }
        ValueInterface val = symTbl.getValueByKey(variable);
        ValueInterface val2 = exp.evaluate(symTbl, heap);
        if(!val.getType().equals(new IntType()))
            throw new MyException("Variable not of type int!");
        if (!val2.getType().equals(new StringType()))
            throw new MyException("Not of type String!");
        StringValue val3 = (StringValue) val2;
        if(!fileTbl.isDefined(val3.getVal()))
            throw new MyException("File already opened!");
        try {
            BufferedReader buff = fileTbl.getValueByKey(val3.getVal());
            String line = buff.readLine();
            if(line == null)
            {
                symTbl.update(variable,new IntValue(0));
            }
            else{
                symTbl.update(variable,new IntValue(Integer.parseInt(line)));
            }
        } catch (FileNotFoundException e) {
            throw new MyException(e.getMessage());
        }
        return null;

    }

    @Override
    public StatementInterface deepCopy() {
        return new ReadFileStatement(exp.deepCopy(), variable) {
        };
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
            TypeInterface expressionType = this.exp.typeCheck(typeEnvironment);
            if (!expressionType.equals(new StringType())) {
                throw new MyException("Expression in ReadFileStatement is not of type string.");
            }
            TypeInterface idType = typeEnvironment.getValueByKey(this.variable);
            if (!idType.equals(new IntType())) {
                throw new MyException("Variable in ReadFileStatement is not of type int.");
            }
        return typeEnvironment;
    }
}
