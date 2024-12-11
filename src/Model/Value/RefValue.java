package Model.Value;

import Model.Value.Type.IType;
import Model.Value.Type.RefType;

public class RefValue implements IValue{
    int address;
    IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public IType getLocationType() {
        return locationType;
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType.toString() + ")";
    }

    @Override
    public IValue compose(IValue other, String operation) {
        return null;
    }

    @Override
    public boolean equals(IValue other) {
        return (other instanceof RefValue) && this.address == ((RefValue) other).address && this.getType().equals(other.getType());
    }
    @Override
    public IValue clone() {
        return new RefValue(this.address, this.locationType);
    }
}
