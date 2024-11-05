package Model.Value.Type;

import Model.Value.IValue;
import Model.Value.IntValue;

public class IntType implements IType {
    @Override
    public IValue getDefaultValue() {
        return new IntValue(0);
    }

    @Override
    public String toString() {
        return "IntegerType";
    }

    @Override
    public boolean equals(IType other) {
        return (other instanceof IntType);
    }
}