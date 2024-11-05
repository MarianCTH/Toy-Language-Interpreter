package Model.Value;

import Exception.ToyLangException;
import Model.Value.Type.IType;

public interface IValue {
    String toString();
    IValue compose(IValue other, String operation) throws ToyLangException;
    IType getType();
    boolean equals(IValue other);
    IValue clone();
}