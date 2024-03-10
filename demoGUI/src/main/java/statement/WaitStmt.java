package statement;

import exceptions.MyException;
import expressions.ValExp;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.TypeInterface;
import value.IntValue;

import java.io.IOException;

public class WaitStmt implements StatementInterface{
    private final int number;

    public WaitStmt(int number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        StackInterface<StatementInterface> exeStack = state.getStack();
        if(number!=0)
            exeStack.push(new CompStmt( new PrintStmt(new ValExp(new IntValue(number))), new WaitStmt(number-1)));
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new WaitStmt(number);
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        return typeEnvironment;
    }
    @Override
    public String toString(){
        return String.format("wait(%s)", number);
    }
}
