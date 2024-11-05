package Model.Expression;

import Model.State.PrgState;
import Model.Value.IValue;

public class ValueExp implements IExpression {
    IValue value;

    public ValueExp(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(PrgState state) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}