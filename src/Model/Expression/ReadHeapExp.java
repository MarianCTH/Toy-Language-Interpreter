package Model.Expression;

import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.RefValue;
import Model.Value.Type.IType;
import Model.Value.Type.RefType;
import Exception.ToyLangException;

public class ReadHeapExp implements IExpression{
    IExpression expr;

    public ReadHeapExp(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public IValue evaluate(PrgState state) throws ToyLangException {
        IValue value = expr.evaluate(state);
        if (!(value.getType() instanceof RefType)) {
            throw new ToyLangException("Heap should only be accessed through references");
        }

        RefValue refValue = (RefValue) value;
        int address = refValue.getAddress();

        if (!state.getHeapTable().getContent().containsKey(address)) {
            throw new ToyLangException("Address " + address + " is not defined in the Heap.");
        }

        return state.getHeapTable().read(address);
    }

    @Override
    public String toString() {
        return "readHeap(" + expr.toString() + ")";
    }
}
