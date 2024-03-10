package statement;

import exceptions.MyException;
import model.MapInterface;
import program.ProgramState;
import type.TypeInterface;

public class NopStmt implements StatementInterface{

    public String toString() {return "nop";}
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NopStmt();
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        return typeEnvironment;
    }
}
