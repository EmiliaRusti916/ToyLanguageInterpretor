package expressions;

import exceptions.MyException;
import model.HeapInterface;
import model.MapInterface;
import type.BoolType;
import type.IntType;
import type.StringType;
import type.TypeInterface;
import value.BoolValue;
import value.IntValue;
import value.StringValue;
import value.ValueInterface;

public class ValExp implements ExpInterface{
    public ValueInterface v;

    public ValExp(ValueInterface v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "ValExp(" +
                "v=" + v +
                ')';
    }

    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> symMap, HeapInterface<Integer, ValueInterface> heap) throws MyException {
        if(v.getType().equals(new IntType()))
        {
            //IntValue v2 = (IntValue) v;
            int v1 = ((IntValue) v).getVal();
            return new IntValue(v1);
        }
        else if(v.getType().equals(new BoolType()))
        {
            //BoolValue b1 = (BoolValue) v;
            boolean b1 = ((BoolValue) v).getValue();
            return new BoolValue(b1);
        }
        else if(v.getType().equals(new StringType()))
        {
            String s1 = ((StringValue) v).getVal();
            return new StringValue(s1);
        }
        else throw new MyException("Invalid Value Expression!");
    }

    @Override
    public ExpInterface deepCopy() {
        return new ValExp(v.deepCopy());
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        return this.v.getType();
    }
}
