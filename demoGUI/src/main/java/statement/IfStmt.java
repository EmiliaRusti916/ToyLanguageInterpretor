package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import model.HeapInterface;
import model.MapInterface;
import model.StackInterface;
import program.ProgramState;
import type.BoolType;
import type.TypeInterface;
import value.BoolValue;
import value.ValueInterface;

public class IfStmt implements StatementInterface{
    ExpInterface exp;
    StatementInterface thenStmt;
    StatementInterface elseStmt;

    public IfStmt(ExpInterface exp, StatementInterface t, StatementInterface e){
        this.exp = exp;
        this.thenStmt = t;
        this.elseStmt = e;
    }

    @Override
    public String toString() {
        return "if(" + exp +
                ") then{ " + thenStmt +
                "} else{ " + elseStmt +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> stk = state.getStack();
        MapInterface<String, ValueInterface> symTbl = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface condition = exp.evaluate(symTbl, heap);
        BoolValue a = new BoolValue(true);
        BoolValue b = new BoolValue(false);
        if(!condition.getType().equals(new BoolType()))
            throw new MyException("Conditional expression is not of type Bool!");
        else{
            BoolValue conditionValue = (BoolValue) condition;
            if(a.getValue() == conditionValue.getValue()) {
                stk.push(thenStmt);
            }
            else if(b.getValue() ==conditionValue.getValue()){
                    stk.push(elseStmt);
            }
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new IfStmt(exp.deepCopy(), thenStmt.deepCopy(), elseStmt.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
            TypeInterface typeExpression = this.exp.typeCheck(typeEnvironment);
            if (!typeExpression.equals(new BoolType())) {
                throw new MyException("The condition of IF has not the type bool.");
            }
            this.thenStmt.typeCheck(typeEnvironment.deepCopy());
            this.elseStmt.typeCheck(typeEnvironment.deepCopy());
        return typeEnvironment;
    }
}
