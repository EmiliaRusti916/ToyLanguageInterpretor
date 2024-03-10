package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import expressions.RelationalExp;
import expressions.VarExp;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.IntType;
import type.StringType;
import type.TypeInterface;

import java.io.IOException;
import java.lang.reflect.Type;

public class ForStmt implements StatementInterface{
    private final String variable;
    private final ExpInterface exp1;
    private final ExpInterface exp2;
    private final ExpInterface exp3;
    private final StatementInterface stmt;

    public ForStmt(String variable, ExpInterface exp1, ExpInterface exp2, ExpInterface exp3, StatementInterface stmt) {
        this.variable = variable;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        StackInterface<StatementInterface> exeStack = state.getStack();
        StatementInterface finalStmt = new CompStmt(new AssignStmt("v", exp1), new WhileStmt(new RelationalExp(new VarExp("v"), exp2, 1),
                new CompStmt(stmt, new AssignStmt("v", exp3))));
        exeStack.push(finalStmt);
        return null;

    }

    @Override
    public StatementInterface deepCopy() {
        return new ForStmt(variable, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy(), stmt.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface typeExp1 = exp1.typeCheck(typeEnvironment);
        TypeInterface typeExp2 = exp2.typeCheck(typeEnvironment);
        TypeInterface typeExp3 = exp3.typeCheck(typeEnvironment);

        if(typeExp1.equals(new IntType()) && typeExp2.equals(new IntType()) && typeExp3.equals(new IntType()))
            return typeEnvironment;
        else
            throw new MyException("Expressions in ForStmt are not all of IntType!");
    }

    public String toString() {
        return String.format("for(%s=%s; %s<%s; %s=%s) {%s}", variable, exp1, variable, exp2, variable, exp3, stmt);
    }
}
