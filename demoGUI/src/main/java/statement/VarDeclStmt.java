package statement;

import exceptions.MyException;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.BoolType;
import type.IntType;
import type.TypeInterface;
import value.BoolValue;
import value.IntValue;
import value.ValueInterface;

import java.lang.reflect.Type;

public class VarDeclStmt implements StatementInterface {
    String s;
    TypeInterface type;

    @Override
    public String toString() {
        return "VarDeclStmt{" +
                "s='" + s + '\'' +
                ", type=" + type +
                '}';
    }

    public VarDeclStmt(String s, TypeInterface t) {
        this.s = s;
        this.type = t;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> stk = state.getStack();
        MapInterface<String, ValueInterface> symTbl = state.getMap();
        if (symTbl.isDefined(this.s))
            throw new MyException("Variable is already defined!");
        else {
            symTbl.add(s, type.defaultValue());
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new VarDeclStmt(s, type.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        typeEnvironment.add(this.s, this.type);
        return typeEnvironment;
    }
}
