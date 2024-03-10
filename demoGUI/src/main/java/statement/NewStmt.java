package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import expressions.ValExp;
import model.HeapInterface;
import model.MapInterface;
import program.ProgramState;
import type.RefType;
import type.TypeInterface;
import value.RefValue;
import value.ValueInterface;

import java.io.IOException;

public class NewStmt implements StatementInterface{
    private final String varName;
    private final ExpInterface expression;

    public NewStmt(String varName, ExpInterface expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        MapInterface<String, ValueInterface> symTable = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        if (symTable.isDefined(varName)) {
            ValueInterface varValue = symTable.getValueByKey(varName);
            if ((varValue.getType() instanceof RefType)) {
                ValueInterface evaluated = expression.evaluate(symTable, heap);
                TypeInterface locationType = ((RefValue) varValue).getLocationType();
                if (locationType.equals(evaluated.getType())) {
                    int newPosition = heap.add(evaluated);
                    symTable.update(varName, new RefValue(newPosition, locationType));
                } else
                    throw new MyException(String.format("%s not of %s in NewStmt", varName, evaluated.getType()));
            } else {
                throw new MyException("Variable is not of RefType in NewStmt");
            }
        } else {
            throw new MyException("Variable not in symTable in NewStmt");
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NewStmt(varName, expression.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface typeVar = typeEnvironment.getValueByKey(varName);
        TypeInterface typeExpr = expression.typeCheck(typeEnvironment);
        if (typeVar.equals(new RefType(typeExpr)))
            return typeEnvironment;
        else
            throw new MyException("Right hand side and left hand side have different types in NewStmt.");
    }
    @Override
    public String toString() {
        return String.format("new(%s, %s)", varName, expression);
    }
}
