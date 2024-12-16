package Model.Expression;

import ADT.Dictionary.IGenericDictionary;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.Type.IType;

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

    @Override
    public IType typecheck(IGenericDictionary<String, IType> typeEnv) {
        return value.getType();
    }
}