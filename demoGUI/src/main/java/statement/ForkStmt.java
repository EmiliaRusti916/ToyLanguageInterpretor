package statement;

import com.sun.jdi.Value;
import exceptions.MyException;
import model.MapInterface;
import model.MyMap;
import model.MyStack;
import model.StackInterface;
import program.ProgramState;
import type.TypeInterface;
import value.ValueInterface;

import java.io.IOException;
import java.util.HashMap;

public class ForkStmt implements StatementInterface{
    public StatementInterface stmt;

    public ForkStmt(StatementInterface stmt) {
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        MapInterface<String, ValueInterface> symMap = state.getMap();
        StackInterface<StatementInterface> stk = new MyStack<>();
        MapInterface<String, ValueInterface> newMap= new MyMap<>();
        for(String key : symMap.getOnlyKeys())
        {
            newMap.add(key, symMap.getValueByKey(key).deepCopy());
        }
        //int id = state.newID();
        stk.push(stmt);
        return new ProgramState(stk,newMap,state.getOut(), state.getFileTbl(), state.getHeap());

    }

    @Override
    public String toString() {
        return "Fork(" +
                stmt +
                ')';
    }

    @Override
    public StatementInterface deepCopy() {
        return new ForkStmt(this.stmt.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        this.stmt.typeCheck(typeEnvironment.deepCopy());
        return typeEnvironment;
    }
}
