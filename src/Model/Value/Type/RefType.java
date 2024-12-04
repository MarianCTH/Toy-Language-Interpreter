package Model.Value.Type;

import Model.Value.Type.IType;
import Model.Value.RefValue;
import Model.Value.IValue;

public class RefType implements  IType{
    IType inner;
    public RefType(IType inner) {
        this.inner = inner;
    }

    @Override
    public IValue getDefaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public String toString() {
        return "RefType " + this.inner.toString();
    }

    public IType getInner(){
        return inner;
    }

    @Override
    public boolean equals(IType other) {
        return (other instanceof RefType) && ((RefType)other).inner != null && ((RefType) other).inner.equals(this.inner);
    }
}
