package Model.Expression;

import Exception.ToyLangException;
import Model.State.PrgState;
import Model.Value.IValue;

public class VarExp implements IExpression {
    String name;

    public VarExp(String name) {
        this.name = name;
    }

    @Override
    public IValue evaluate(PrgState state) throws ToyLangException {
        return state.getSymTable().getValue(this.name);
    }

    @Override
    public String toString() {
        return name;
    }

}