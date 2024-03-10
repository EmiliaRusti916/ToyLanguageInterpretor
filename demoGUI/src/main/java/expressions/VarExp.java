package expressions;

import exceptions.MyException;
import model.HeapInterface;
import model.MapInterface;
import type.TypeInterface;
import value.ValueInterface;

public class VarExp implements ExpInterface{
    public String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "VarExp(" +
                "id='" + id + '\'' +
                ')';
    }

    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> symMap, HeapInterface<Integer, ValueInterface> heap) throws MyException {
        if(symMap.getValueByKey(id) != null)
            return symMap.getValueByKey(id);
        else throw new MyException("Variable not defined!");
    }

    @Override
    public ExpInterface deepCopy() {
        return new VarExp(id);
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        return typeEnvironment.getValueByKey(this.id);
    }
}
