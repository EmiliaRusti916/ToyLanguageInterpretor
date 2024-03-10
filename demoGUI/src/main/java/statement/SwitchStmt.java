package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import expressions.RelationalExp;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.TypeInterface;

import java.io.IOException;
import java.lang.reflect.Type;

public class SwitchStmt implements StatementInterface{
    private final ExpInterface exp;
    private final ExpInterface exp1;
    private final ExpInterface exp2;
    private final StatementInterface stmt1;
    private final StatementInterface stmt2;
    private final StatementInterface stmt3;

    public SwitchStmt(ExpInterface exp, ExpInterface exp1, ExpInterface exp2, StatementInterface stmt1, StatementInterface stmt2, StatementInterface stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        StackInterface<StatementInterface> exeStack = state.getStack();
        StatementInterface finalStmt = new IfStmt(new RelationalExp(exp, exp1, 3),stmt1, new IfStmt(new RelationalExp(exp, exp2, 3),stmt3, stmt3));
        exeStack.push(finalStmt);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new SwitchStmt(exp.deepCopy(), exp1.deepCopy(), exp2.deepCopy(), stmt1.deepCopy(), stmt2.deepCopy(), stmt3.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface expType = exp.typeCheck(typeEnvironment);
        TypeInterface exp1Type = exp1.typeCheck(typeEnvironment);
        TypeInterface exp2Type = exp2.typeCheck(typeEnvironment);
        if(expType.equals(exp1Type) && expType.equals(exp2Type))
        {
            stmt1.typeCheck(typeEnvironment.deepCopy());
            stmt2.typeCheck(typeEnvironment.deepCopy());
            stmt3.typeCheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else throw new MyException("Expression types in SwitchStmt do not match!");
    }
    @Override
    public String toString(){
        return String.format("switch(%s)(case %s: %s)(case %s: %s)(default: %s)", exp, exp1, stmt1, exp2,stmt2,stmt3);
    }
}
