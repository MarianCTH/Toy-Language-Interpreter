package Model.Value.Type;

import Model.Value.IValue;
import Model.Value.StringValue;

public class StringType implements IType{
    @Override
    public IValue getDefaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "StringType";
    }

    @Override
    public boolean equals(IType other) {
        return (other instanceof StringType);
    }
}
