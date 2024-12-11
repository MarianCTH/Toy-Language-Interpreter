package Model.Value;

import Exception.ToyLangException;
import Exception.InvalidOperationException;

import Model.Value.Type.IType;
import Model.Value.Type.StringType;

import java.util.Objects;


public class StringValue implements IValue{
    String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(IValue other) {
        if (other.getType() instanceof StringType) {
            return Objects.equals(this.getValue(), ((StringValue) other).getValue());
        }
        return false;
    }

    private StringValue add(StringValue other) {
        return new StringValue(this.value + other.getValue());
    }

    private BoolValue lessThan(StringValue other) {
        return new BoolValue(this.value.compareTo(other.value) < 0);
    }

    private BoolValue lessThanEqual(StringValue other) {
        return new BoolValue(this.value.compareTo(other.value) <= 0);
    }

    private BoolValue greaterThan(StringValue other) {
        return new BoolValue(this.value.compareTo(other.value) > 0);
    }

    private BoolValue greaterThanEqual(StringValue other) {
        return new BoolValue(this.value.compareTo(other.value) >= 0);
    }

    private BoolValue equal(StringValue other) {
        return new BoolValue(this.equals(other));
    }

    private BoolValue notEqual(StringValue other) {
        return new BoolValue(!this.equals(other));
    }

    @Override
    public IValue compose(IValue other, String operation) throws ToyLangException {
        if (!(other.getType().equals(this.getType()))) {
            throw new InvalidOperationException("InvalidOperationException: Cannot compose two different types using operator " + operation);
        }
        return switch (operation) {
            case "+" -> this.add((StringValue) other);
            case "<" -> this.lessThan((StringValue) other);
            case "<=" -> this.lessThanEqual((StringValue) other);
            case "==" -> this.equal((StringValue) other);
            case "!=" -> this.notEqual((StringValue) other);
            case ">" -> this.greaterThan((StringValue) other);
            case ">=" -> this.greaterThanEqual((StringValue) other);
            default ->
                    throw new InvalidOperationException("InvalidOperationException: Cannot compose two StringValue types using operation " + operation);
        };
    }

    @Override
    public IValue clone() {
        return new StringValue(this.value);
    }
}
