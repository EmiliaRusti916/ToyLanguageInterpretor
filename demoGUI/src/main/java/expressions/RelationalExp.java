package expressions;

import exceptions.MyException;
import model.HeapInterface;
import model.MapInterface;
import type.BoolType;
import type.IntType;
import type.TypeInterface;
import value.BoolValue;
import value.IntValue;
import value.ValueInterface;

public class RelationalExp implements ExpInterface{
    ExpInterface exp1;
    ExpInterface exp2;
    int op;

    public RelationalExp(ExpInterface exp1, ExpInterface exp2, int op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> symMap, HeapInterface<Integer, ValueInterface> heap) throws MyException {
        ValueInterface n1 = exp1.evaluate(symMap, heap);
        if(n1.getType().equals(new IntType()))
        {
            ValueInterface n2 = exp2.evaluate(symMap, heap);
            if(n2.getType().equals(new IntType()))
            {
                int i1, i2;
                i1 = ((IntValue) n1).getVal();
                i2 = ((IntValue) n2).getVal();
                if(op == 1) return new BoolValue(i1<i2);
                else if(op == 2) return new BoolValue(i1<=i2);
                else if(op == 3) return new BoolValue(i1==i2);
                else if(op == 4) return new BoolValue(i1!=i2);
                else if(op == 5) return new BoolValue(i1>i2);
                else if(op == 6) return new BoolValue(i1>=i2);

                else throw new MyException("Invalid operator");
            }
            else throw new MyException("Second operand is not int!");
        }
        else throw new MyException("First operand is not int!");
    }

    @Override
    public ExpInterface deepCopy() {
        return new RelationalExp(exp1.deepCopy(), exp2.deepCopy(), op);
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

        return new BoolType();
    }

    @Override
    public String toString() {
        return "RelationalExp{" +
                "exp1=" + exp1 +
                ", exp2=" + exp2 +
                ", op=" + op +
                '}';
    }
}
