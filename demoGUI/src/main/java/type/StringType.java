package type;

import value.StringValue;
import value.ValueInterface;

import java.lang.reflect.Type;

public class StringType implements TypeInterface {

    public boolean equals(Object other){
        return other instanceof StringType;
    }

    @Override
    public TypeInterface deepCopy() {
        return new StringType();
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public ValueInterface defaultValue() {
        return new StringValue("");
    }
}
