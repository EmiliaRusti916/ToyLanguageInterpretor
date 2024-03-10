package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.BoolType;
import type.TypeInterface;

import java.io.IOException;

public class DoWhileStmt implements StatementInterface {
    private final StatementInterface stmt;
    private final ExpInterface exp;

    public DoWhileStmt(StatementInterface stmt, ExpInterface exp) {
        this.stmt = stmt;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        StackInterface<StatementInterface> exeStack = state.getStack();
        StatementInterface finalStmt = new CompStmt(stmt, new WhileStmt(exp, stmt));
        exeStack.push(finalStmt);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new DoWhileStmt(stmt.deepCopy(), exp.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface type1 = exp.typeCheck(typeEnvironment);
        if (type1.equals(new BoolType())) {
            stmt.typeCheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        } else throw new MyException("Expression is not of type Bool in DoWhileStmt!");
    }

    @Override
    public String toString() {
        return String.format("do {%s} while (%s)", stmt, exp);
    }
}
