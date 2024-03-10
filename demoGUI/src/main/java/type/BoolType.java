package type;

import value.BoolValue;
import value.ValueInterface;

public class BoolType implements TypeInterface{
    public boolean equals(Object other){
        return other instanceof BoolType;
    }

    @Override
    public TypeInterface deepCopy() {
        return new BoolType();
    }

    public String toString(){
        return "boolean";
    }

    @Override
    public ValueInterface defaultValue() {
        return new BoolValue(false);
    }
}
