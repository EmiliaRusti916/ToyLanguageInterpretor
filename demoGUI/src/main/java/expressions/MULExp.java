package expressions;

import exceptions.MyException;
import model.HeapInterface;
import model.MapInterface;
import statement.StatementInterface;
import type.IntType;
import type.TypeInterface;
import value.ValueInterface;

public class MULExp implements ExpInterface{
    private final ExpInterface exp1;
    private final ExpInterface exp2;

    public MULExp(ExpInterface exp1, ExpInterface exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> symMap, HeapInterface<Integer, ValueInterface> heap) throws MyException {
        ExpInterface finalStmt = new ArthExp(new ArthExp(exp1, exp2, 3), new ArthExp(exp1, exp2,1), 2);
        return finalStmt.evaluate(symMap,heap);
    }

    @Override
    public ExpInterface deepCopy() {
        return new MULExp(exp1.deepCopy(), exp2.deepCopy());
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface type1 = exp1.typeCheck(typeEnvironment);
        TypeInterface type2 = exp2.typeCheck(typeEnvironment);
        if(type1.equals(new IntType()) && type2.equals(new IntType()))
            return new IntType();
        else throw new MyException("Expressions in MULExp are not of IntType!");
    }

    @Override
    public String toString() {
        return String.format("MUL(%s,%s)", exp1, exp2);
    }
}
