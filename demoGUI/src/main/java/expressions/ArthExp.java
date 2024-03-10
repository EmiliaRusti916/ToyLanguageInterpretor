package expressions;

import exceptions.MyException;
import model.HeapInterface;
import model.MapInterface;
import type.IntType;
import type.TypeInterface;
import value.IntValue;
import value.ValueInterface;

public class ArthExp implements ExpInterface {
    ExpInterface exp1;
    ExpInterface exp2;
    int operator; //1+ 2- 3* 4/

    public ArthExp(ExpInterface exp1, ExpInterface exp2, int op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = op;
    }

    @Override
    public String toString() {
        return "ArthExp(" +
                "exp1=" + exp1 +
                ", exp2=" + exp2 +
                ", operator=" + operator +
                ')';
    }


    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> symMap, HeapInterface<Integer, ValueInterface> heap) throws MyException {
        ValueInterface v1, v2;
        v1 = exp1.evaluate(symMap, heap);
        try {
            if (v1.getType().equals(new IntType())) {
                v2 = exp2.evaluate(symMap, heap);
                if (v2.getType().equals(new IntType())) {
                    IntValue i1 = (IntValue) v1;
                    IntValue i2 = (IntValue) v2;
                    int n1, n2;
                    n1 = i1.getVal();
                    n2 = i2.getVal();
                    if (operator == 1) return new IntValue(n1 + n2);
                    if (operator == 2) return new IntValue(n1 - n2);
                    if (operator == 3) return new IntValue(n1 * n2);
                    if (operator == 4) {
                        if (n2 == 0)
                            throw new MyException("Division By Zero!");
                        else return new IntValue(n1 / n2);
                    } else throw new MyException("Operator Invalid.");
                } else throw new MyException("Second operand NOT an INTEGER!");
            } else throw new MyException("First operand NOT an INTEGER!");
        } catch (MyException e) {
            throw new MyException("Invalid Arithmetic expression!");
        }
    }

    @Override
    public ExpInterface deepCopy() {
        return new ArthExp(exp1.deepCopy(), exp2.deepCopy(), operator);
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface type1 = this.exp1.typeCheck(typeEnvironment);
        TypeInterface type2 = this.exp2.typeCheck(typeEnvironment);

        if (!type1.equals(new IntType())) {
            throw new MyException("First operand is not an integer");
        }
        if (!type2.equals(new IntType())) {
            throw new MyException("Second operand is not an integer");
        }

        return new IntType();
    }
}
