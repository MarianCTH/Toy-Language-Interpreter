package Model.Value;

import Exception.DivisionOverflowException;
import Exception.InvalidOperationException;
import Model.Value.Type.IType;
import Model.Value.Type.IntType;

public class IntValue implements IValue {
    int value;

    public IntValue() {
        this.value = 0;
    }

    public IntValue(int value) {
        this.value = value;
    }

    private IntValue add(IntValue other) {
        return new IntValue(this.value + other.value);
    }

    private IntValue subtract(IntValue other) {
        return new IntValue(this.value - other.value);
    }

    private IntValue multiply(IntValue other) {
        return new IntValue(this.value * other.value);
    }

    private IntValue divide(IntValue other) throws DivisionOverflowException {
        if (other.value == 0) {
            throw new DivisionOverflowException("DivisionOverflowAppException: Cannot divide by 0");
        }
        return new IntValue(this.value / other.value);
    }

    private BoolValue lessThan(IntValue other) {
        return new BoolValue(this.value < other.value);
    }

    private BoolValue lessThanEqual(IntValue other) {
        return new BoolValue(this.value <= other.value);
    }

    private BoolValue greaterThan(IntValue other) {
        return new BoolValue(this.value > other.value);
    }

    private BoolValue greaterThanEqual(IntValue other) {
        return new BoolValue(this.value >= other.value);
    }

    private BoolValue equal(IntValue other) {
        return new BoolValue(this.equals(other));
    }

    private BoolValue notEqual(IntValue other) {
        return new BoolValue(!this.equals(other));
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public IValue compose(IValue other, String operation) throws InvalidOperationException, DivisionOverflowException {
        if (!(other.getType().equals(this.getType()))) {
            throw new InvalidOperationException("InvalidOperationAppException: Cannot compose two different types using operator " + operation);
        }
        switch (operation) {
            case "+":
                return this.add((IntValue) other);
            case "-":
                return this.subtract((IntValue) other);
            case "*":
                return this.multiply((IntValue) other);
            case "/":
                return this.divide((IntValue) other);
            case "<":
                return this.lessThan((IntValue) other);
            case "<=":
                return this.lessThanEqual((IntValue) other);
            case "==":
                return this.equal((IntValue) other);
            case "!=":
                return this.notEqual((IntValue) other);
            case ">":
                return this.greaterThan((IntValue) other);
            case ">=":
                return this.greaterThanEqual((IntValue) other);
        }
        throw new InvalidOperationException("InvalidOperationAppException: Cannot compose two IntegerValue types using operator " + operation);
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public boolean equals(IValue other) {
        if (other.getType() instanceof IntType) {
            return this.getValue() == ((IntValue) other).getValue();
        }
        return false;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public IValue clone() {
        return new IntValue(this.value);
    }
}