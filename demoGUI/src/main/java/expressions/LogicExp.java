package expressions;

import exceptions.MyException;
import model.HeapInterface;
import model.MapInterface;
import type.BoolType;
import type.IntType;
import type.TypeInterface;
import value.BoolValue;
import value.ValueInterface;

public class LogicExp implements ExpInterface{
    ExpInterface exp1;
    ExpInterface exp2;
    int op;

    public LogicExp(ExpInterface exp1, ExpInterface exp2, int op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public String toString() {
        return "LogicExp(" +
                "exp1=" + exp1 +
                ", exp2=" + exp2 +
                ", op=" + op +
                ')';
    }

    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> symMap, HeapInterface<Integer, ValueInterface> heap) throws MyException {
        ValueInterface n1 = exp1.evaluate(symMap, heap);
        if(n1.getType().equals(new BoolType()))
        {
            ValueInterface n2 = exp2.evaluate(symMap, heap);
            if(n2.getType().equals(new BoolType()))
            {
                boolean b1, b2;
                b1 = ((BoolValue) n1).getValue();
                b2 = ((BoolValue) n2).getValue();
                if(op == 1) return new BoolValue(b1&&b2);
                else if(op == 2) return new BoolValue(b1||b2);
                else throw new MyException("Invalid operator");
            }
            else throw new MyException("Second operand is not boolean!");
        }
        else throw new MyException("First operand is not boolean!");
    }

    @Override
    public ExpInterface deepCopy() {
        return new LogicExp(exp1.deepCopy(), exp2.deepCopy(), op);
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface type1 = this.exp1.typeCheck(typeEnvironment);
        TypeInterface type2 = this.exp2.typeCheck(typeEnvironment);

        if (!type1.equals(new BoolType())) {
            throw new MyException("First operand is not a bool");
        }
        if (!type2.equals(new BoolType())) {
            throw new MyException("Second operand is not a bool");
        }

        return new BoolType();
    }
}
