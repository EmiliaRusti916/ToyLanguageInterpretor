package statement;

import exceptions.MyException;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.TypeInterface;

import java.io.IOException;
import java.util.Stack;

public class SleepStmt implements StatementInterface{
    private final int number;

    public SleepStmt(int number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        StackInterface<StatementInterface> exeStack = state.getStack();
        if(number!=0)
            exeStack.push(new SleepStmt(number-1));
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new SleepStmt(number);
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return String.format("sleep(%s)", number);
    }
}
