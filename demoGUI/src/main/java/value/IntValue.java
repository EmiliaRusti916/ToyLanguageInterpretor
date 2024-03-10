package value;

import type.IntType;
import type.TypeInterface;

public class IntValue implements ValueInterface{
    int val;
    public IntValue(int v){
       this.val = v;
    }
    @Override
    public TypeInterface getType() {
        return new IntType();
    }
    public int getVal(){
        return this.val;
    }
    public String toString(){
        return String.valueOf(this.val);
    }

    public boolean equals(Object other){
        return other instanceof IntValue && ((IntValue)other).getVal() == val;
    }
    public ValueInterface deepCopy() { return new IntValue(this.val);}
}
