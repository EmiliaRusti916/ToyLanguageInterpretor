package expressions;

import exceptions.MyException;
import model.HeapInterface;
import model.MapInterface;
import type.RefType;
import type.TypeInterface;
import value.RefValue;
import value.ValueInterface;

public class HeapReadExp implements ExpInterface{
    private final ExpInterface exp;

    public HeapReadExp(ExpInterface exp) {
        this.exp = exp;
    }

    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> symMap, HeapInterface<Integer, ValueInterface> heap) throws MyException {
        ValueInterface val = exp.evaluate(symMap, heap);
        if (val instanceof RefValue) {
            int address = ((RefValue) val).getAddress();
            if (heap.isDefined(address)) {
                ValueInterface valueHeap = heap.getValueByKey(address);
                return valueHeap;
            } else throw new MyException("The address was not defined");
        } else
            throw new MyException("the value is not a RefValue");
    }

    @Override
    public String toString() {
        return "HeapReadExp(" +
                "exp=" + exp +
                ')';
    }

    @Override
    public ExpInterface deepCopy() {
        return new HeapReadExp(this.exp.deepCopy());
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface type = this.exp.typeCheck(typeEnvironment);
        if (!(type instanceof RefType)) {
            throw new MyException("HeaoReadExp is not a reference type.");
        }

        return ((RefType) type).getInner();
    }
}
