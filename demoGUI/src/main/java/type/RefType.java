package type;

import value.RefValue;
import value.ValueInterface;

import java.sql.Ref;

public class RefType implements TypeInterface{
    TypeInterface inner;

    public RefType(TypeInterface inner) {
        this.inner = inner;
    }

    public TypeInterface getInner() {
        return this.inner;

    }
    @Override
    public ValueInterface defaultValue() {
        return new RefValue(0,inner);
    }

    @Override
    public String toString() {
        return "RefType(" +
                inner.toString() +
                ')';
    }

    @Override
    public TypeInterface deepCopy() {
        return new RefType(this.inner);
    }
    public boolean equals(Object another){
        return another instanceof RefType && inner.equals(((RefType) another).getInner());
    }
}
