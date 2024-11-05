package Model.Value.Type;

import Model.Value.BoolValue;
import Model.Value.IValue;

public class BoolType implements IType {
    @Override
    public IValue getDefaultValue() {
        return new BoolValue(false);
    }

    @Override
    public String toString() {
        return "BooleanType";
    }

    @Override
    public boolean equals(IType other) {
        return (other instanceof BoolType);
    }
}