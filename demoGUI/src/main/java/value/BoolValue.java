package value;

import type.BoolType;
import type.TypeInterface;

public class BoolValue implements ValueInterface{
    boolean val;
    public BoolValue(boolean v){
        this.val = v;
    }
    public boolean getValue(){
        return this.val;
    }
    public String toString(){
        return String.valueOf(this.val);
    }
    @Override
    public TypeInterface getType() {
        return new BoolType();
    }
    public boolean equals(Object other){
        return other instanceof BoolValue && ((BoolValue)other).getValue() == val;
    }
    public ValueInterface deepCopy() { return new BoolValue(this.val);}
}
