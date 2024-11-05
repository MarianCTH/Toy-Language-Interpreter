package Model.Value;

import Exception.InvalidOperationException;
import Model.Value.Type.BoolType;
import Model.Value.Type.IType;

public class BoolValue implements IValue {
    boolean value;

    public BoolValue() {
        this.value = false;
    }

    public BoolValue(boolean value) {
        this.value = value;
    }

    private BoolValue and(BoolValue other) {
        return new BoolValue(this.value & other.value);
    }

    private BoolValue or(BoolValue other) {
        return new BoolValue(this.value | other.value);
    }


    private BoolValue equal(BoolValue other) {
        return new BoolValue(this.equals(other));
    }

    private BoolValue notEqual(BoolValue other) {
        return new BoolValue(!this.equals(other));
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public IValue compose(IValue other, String operation) throws InvalidOperationException {
        if (!(other.getType().equals(this.getType()))) {
            throw new InvalidOperationException("InvalidOperationAppException: Cannot compose two different types using operation " + operation);
        }
        switch (operation) {
            case "and":
                return this.and((BoolValue) other);
            case "or":
                return this.or((BoolValue) other);
            case "==":
                return this.equal((BoolValue) other);
            case "!=":
                return this.notEqual((BoolValue) other);
        }
        throw new InvalidOperationException("InvalidOperationAppException: Cannot compose two BooleanValue types using operation " + operation);
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(IValue other) {
        if (other.getType() instanceof BoolType) {
            return this.getValue() == ((BoolValue) other).getValue();
        }
        return false;
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public IValue clone() {
        return new BoolValue(this.value);
    }
}