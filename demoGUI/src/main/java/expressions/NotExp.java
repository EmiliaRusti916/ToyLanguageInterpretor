package expressions;

import exceptions.MyException;
import model.HeapInterface;
import model.MapInterface;
import type.TypeInterface;
import value.BoolValue;
import value.ValueInterface;

public class NotExp implements ExpInterface{
    private final ExpInterface exp;

    public NotExp(ExpInterface exp) {
        this.exp = exp;
    }

    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> symMap, HeapInterface<Integer, ValueInterface> heap) throws MyException {
        BoolValue value = (BoolValue) exp.evaluate(symMap,heap);
        if(!value.getValue())
            return new BoolValue(true);
        else
            return new BoolValue(false);
    }

    @Override
    public ExpInterface deepCopy() {
        return new NotExp(exp.deepCopy());
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        return exp.typeCheck(typeEnvironment);
    }
    @Override
    public String toString(){
        return String.format("!(%s)", exp);
    }
}
