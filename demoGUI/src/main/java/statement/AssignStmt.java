package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import model.HeapInterface;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.TypeInterface;
import value.ValueInterface;

public class AssignStmt implements StatementInterface{
    String id;
    ExpInterface exp;

    @Override
    public String toString() {
        return "AssignStmt(" + id + '=' + exp + ')';
    }

    public AssignStmt(String i, ExpInterface e){
        this.id = i;
        this.exp = e;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> stk = state.getStack();
        MapInterface<String, ValueInterface> symTbl = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        if(symTbl.isDefined(id))
        {
            ValueInterface val = exp.evaluate(symTbl, heap);
            TypeInterface typeID = (symTbl.getValueByKey(id)).getType();
            if(val.getType().equals(typeID))
                symTbl.update(id, val);
            else
                throw new MyException("Declared type of the variable" + id + " and type " +
                        "of the assigned expression do not match!");
        }
        else throw new MyException("The variable" + id + "was not declared before.");
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface typeVar = typeEnvironment.getValueByKey(this.id);
        TypeInterface typeExp = exp.typeCheck(typeEnvironment);
        if(typeVar.equals(typeExp))
            return typeEnvironment;
        else
            throw new MyException("AssignStmt: right hand side and left hand side have different types");
    }
}
