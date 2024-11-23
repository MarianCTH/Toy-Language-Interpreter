package Model.Statement;

import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.RefValue;
import Model.Value.Type.RefType;
import Exception.ToyLangException;

public class WriteHeap implements IStatement {
    IExpression addressExpression;
    IExpression valueExpression;

    public WriteHeap(IExpression addressExpression, IExpression valueExpression) {
        this.addressExpression = addressExpression;
        this.valueExpression = valueExpression;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        IValue address = addressExpression.evaluate(state);
        IValue value = valueExpression.evaluate(state);
        if (!(address.getType() instanceof RefType)) {
            throw new ToyLangException("Heap should be accessed only using references");
        }
        state.getHeapTable().write(((RefValue) address).getAddress(), value);
        return null;
    }

    @Override
    public String toString() {
        return "writeHeap(" + addressExpression.toString() + ", " + valueExpression.toString() + ")";
    }
}
