package Model.Statement;

import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.RefValue;
import Model.Value.Type.RefType;
import Exception.ToyLangException;

import java.util.Map;

public class WriteHeap implements IStatement {
    IExpression addressExpression;
    IExpression valueExpression;

    public WriteHeap(IExpression addressExpression, IExpression valueExpression) {
        this.addressExpression = addressExpression;
        this.valueExpression = valueExpression;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        Map<String, IValue> symTableContent = state.getSymTable().getContent();
        IValue address = addressExpression.evaluate(state);
        if (!(address instanceof RefValue)) {
            throw new ToyLangException("Heap should be accessed only using references");
        }

        RefValue refValue = (RefValue) address;
        int heapAddress = refValue.getAddress();

        if (!state.getHeapTable().getContent().containsKey(heapAddress)) {
            throw new ToyLangException("Address " + heapAddress + " is not defined in the Heap.");
        }

        IValue value = valueExpression.evaluate(state);
        RefType refType = (RefType) refValue.getType();
        if (!value.getType().equals(refType.getInner())) {
            throw new ToyLangException("Type of the evaluated expression does not match the location type.");
        }

        state.getHeapTable().write(heapAddress, value);
        return null;
    }

    @Override
    public String toString() {
        return "writeHeap(" + addressExpression.toString() + ", " + valueExpression.toString() + ")";
    }
}