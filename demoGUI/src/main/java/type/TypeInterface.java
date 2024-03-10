package type;
import value.ValueInterface;
public interface TypeInterface {
    ValueInterface defaultValue();
    boolean equals(Object other);
    TypeInterface deepCopy();
}
