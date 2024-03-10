package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import expressions.ValExp;
import model.HeapInterface;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.BoolType;
import type.TypeInterface;
import value.BoolValue;
import value.ValueInterface;

import java.io.IOException;

public class WhileStmt implements StatementInterface{
    private final ExpInterface exp;
    private final StatementInterface statement;

    public WhileStmt(ExpInterface exp, StatementInterface statement) {
        this.exp = exp;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        StackInterface<StatementInterface> stack = state.getStack();
        MapInterface<String, ValueInterface> symTbl = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        try{
            ValueInterface expValue = this.exp.evaluate(symTbl, heap);
            if(!expValue.getType().equals(new BoolType()))
                throw new MyException("Expression is not of type bool!");
            BoolValue boolValue = (BoolValue) expValue;
            if(boolValue.getValue())
            {
                stack.push(this);
                stack.push(this.statement);
            }
        }catch(MyException e)
        {
            throw new MyException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "While(" +
                exp.toString() +
                ")" + statement;
    }

    @Override
    public StatementInterface deepCopy() {
        return new WhileStmt(this.exp.deepCopy(), this.statement.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
            TypeInterface expressionType = this.exp.typeCheck(typeEnvironment);
            if (!expressionType.equals(new BoolType())) {
                throw new MyException("Expression " + this.exp + " is not a boolean");
            }
            this.statement.typeCheck(typeEnvironment.deepCopy());
        return typeEnvironment;
    }
}
