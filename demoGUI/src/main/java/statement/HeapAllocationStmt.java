package statement;

import exceptions.MyException;
import expressions.ExpInterface;
import model.HeapInterface;
import model.MapInterface;
import model.MyHeap;
import program.ProgramState;
import type.RefType;
import type.TypeInterface;
import value.RefValue;
import value.ValueInterface;

import java.io.IOException;

public class HeapAllocationStmt implements StatementInterface{
    private final String var;
    private final ExpInterface exp;

    public HeapAllocationStmt(String var, ExpInterface exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        MapInterface<String, ValueInterface> map = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        /*if(!symTbl.isDefined(this.var))
            throw new MyException("Variable name " + this.var + " not defined!");
        TypeInterface varValue = symTbl.getValueByKey(this.var).getType();
        if(!(varValue instanceof RefType))
            throw new MyException("Invalid type in allocation!");
        //if(!varValue.getType().equals(new RefType(null)))
          //  throw new MyException("Invalid type in allocation!");

        try{
            //ValueInterface expValue = this.exp.evaluate(symTbl, heap);
            //RefValue refValue = (RefValue) varValue;
            //if(!expValue.getType().equals(refValue.getLocationType()))
              //  throw new MyException("Location type and variable type do not match!");
            TypeInterface locType = ((RefType) varValue).getInner();
            ValueInterface val = exp.evaluate(symTbl,heap);
            if(val.getType().equals(locType)){

                int addr = heap.add(val);
                symTbl.update(this.var, new RefValue(addr, locType));
            }
        }catch (MyException e) {
            throw new MyException(e.getMessage());
        }
         */
        if (map.isDefined(var)) {
            TypeInterface typeId = (map.getValueByKey(var).getType());
            if (typeId instanceof RefType) {
                TypeInterface locationType = ((RefType) typeId).getInner();
                ValueInterface val = exp.evaluate(map, state.getHeap());
                if(val.getType().equals(locationType)){
                    int adress_new=heap.add(val);
                    map.update(var,new RefValue(adress_new,locationType));}

            }
            else {

                throw new MyException("declared type of variable id and type of the assigned expression do not match");
            }
        } else throw new MyException("the use of the variable id was not declared before");
        return null;
    }

    @Override
    public String toString() {
        return "HeapAllocationStatement(" +
                "var='" + var + '\'' +
                ", exp=" + exp +
                ')';
    }

    @Override
    public StatementInterface deepCopy() {
        return new HeapAllocationStmt(this.var, this.exp.deepCopy());
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnvironment) throws MyException {
            TypeInterface typeVariable = typeEnvironment.getValueByKey(this.var);
            TypeInterface typeExpression = this.exp.typeCheck(typeEnvironment);
            if (!typeVariable.equals(new RefType(typeExpression)))
                throw new MyException("Declared type of variable and type of the assigned expression do not match in HeapAllocationStmt.");
        return typeEnvironment;
    }
}
