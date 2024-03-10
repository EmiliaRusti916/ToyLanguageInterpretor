package value;

import type.RefType;
import type.TypeInterface;

public class RefValue implements ValueInterface{
    int address;
    TypeInterface locationType;

    public RefValue(int address, TypeInterface locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public TypeInterface getType() {
        return new RefType(locationType);
    }

    public int getAddress() {
        return address;
    }

    public TypeInterface getLocationType() {
        return locationType;
    }

    @Override
    public ValueInterface deepCopy() {
        return new RefValue(this.address, this.locationType);
    }

    @Override
    public String toString() {
        return "RefValue{" +
                "address=" + address + " locationType=" + locationType +
                '}';
    }
}
