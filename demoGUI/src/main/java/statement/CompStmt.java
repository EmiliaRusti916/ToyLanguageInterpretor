package statement;

import exceptions.MyException;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.TypeInterface;

public class CompStmt implements StatementInterface{
    StatementInterface first;
    StatementInterface second;

    public CompStmt(StatementInterface f, StatementInterface s){
        this.first = f;
        this.second = s;
    }


    @Override
    public String toString(){
        return "("+first.toString()+";"+second.toString()+")";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> exeStack = state.getStack();
        exeStack.push(second);
        exeStack.push(first);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        return this.second.typeCheck(this.first.typeCheck(typeEnvironment));
    }
}
