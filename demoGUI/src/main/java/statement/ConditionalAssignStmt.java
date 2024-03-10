package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import expressions.VarExp;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.BoolType;
import type.IntType;
import type.TypeInterface;

import java.io.IOException;

public class ConditionalAssignStmt implements StatementInterface{
    public final String variable;
    public final ExpInterface exp1;
    public final ExpInterface exp2;
    public final ExpInterface exp3;

    public ConditionalAssignStmt(String variable, ExpInterface exp1, ExpInterface exp2, ExpInterface exp3) {
        this.variable = variable;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        StackInterface<StatementInterface> exeStack = state.getStack();
        StatementInterface finalStmt = new IfStmt(exp1, new AssignStmt(variable, exp2), new AssignStmt(variable, exp3));
        exeStack.push(finalStmt);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new ConditionalAssignStmt(variable, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface variableType = new VarExp(variable).typeCheck(typeEnvironment);
        TypeInterface type1 = exp1.typeCheck(typeEnvironment);
        TypeInterface type2 = exp2.typeCheck(typeEnvironment);
        TypeInterface type3 = exp3.typeCheck(typeEnvironment);
        if(type1.equals(new BoolType()) && type2.equals(variableType) && type3.equals(variableType))
            return typeEnvironment;
        else throw new MyException("Expression types in ConditionalAssignStmt are invalid!");
    }
    @Override
    public String toString(){
        return String.format("%s=%s?%s:%s",variable, exp1, exp2, exp3);
    }
}
