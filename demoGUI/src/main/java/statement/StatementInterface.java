package statement;

import model.MapInterface;
import program.ProgramState;
import exceptions.MyException;
import type.TypeInterface;

import java.io.IOException;
import java.lang.reflect.Type;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws MyException, IOException;
    StatementInterface deepCopy();

    MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException;
}
