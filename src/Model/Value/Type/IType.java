package Model.Value.Type;

import Model.Value.IValue;

public interface IType {
    IValue getDefaultValue();
    boolean equals(IType other);
}
