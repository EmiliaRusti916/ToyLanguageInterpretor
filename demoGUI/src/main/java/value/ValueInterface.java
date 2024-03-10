package value;

import type.TypeInterface;

public interface ValueInterface {
    TypeInterface getType();
    ValueInterface deepCopy();
}
