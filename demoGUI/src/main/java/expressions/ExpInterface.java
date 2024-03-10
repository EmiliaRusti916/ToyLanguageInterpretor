package expressions;

import exceptions.MyException;
import model.HeapInterface;
import model.MapInterface;
import type.TypeInterface;
import value.ValueInterface;

public interface ExpInterface {
    ValueInterface evaluate(MapInterface<String,ValueInterface> symMap, HeapInterface<Integer, ValueInterface> heap) throws MyException;
    ExpInterface deepCopy();

    TypeInterface typeCheck(MapInterface<String,TypeInterface> typeEnvironment) throws MyException;
    String toString();
}
