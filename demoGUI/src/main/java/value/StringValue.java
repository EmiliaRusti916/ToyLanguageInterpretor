package value;

import type.StringType;
import type.TypeInterface;

public class StringValue implements ValueInterface{
    String str;

    public StringValue(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return this.str;
    }

    @Override
    public TypeInterface getType() {
        return new StringType();
    }
    public String getVal() { return this.str;}

    public boolean equals(Object other){
        return other instanceof StringValue && ((StringValue)other).toString().equals(str);
    }
    public ValueInterface deepCopy() { return new StringValue(this.str);}
}
