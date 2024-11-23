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
        if (!(value.getType() instanceof RefType))
            throw new ToyLangException ("Heap should only be accessed through references");
        return state.getHeapTable().read(((RefValue) value).getAddress());
    }

    @Override
    public String toString() {
        return "readHeap(" + expr.toString() + ")";
    }
}
