package type;

import value.IntValue;
import value.ValueInterface;

public class IntType implements TypeInterface{
    public boolean equals(Object other){
        return other instanceof IntType;
    }

    @Override
    public TypeInterface deepCopy() {
        return new IntType();
    }

    public String toString(){
        return "int";
    }

    @Override
    public ValueInterface defaultValue() {
        return new IntValue(0);
    }
}
