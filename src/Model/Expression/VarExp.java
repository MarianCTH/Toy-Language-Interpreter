package Model.Expression;

import ADT.Dictionary.IGenericDictionary;
import Exception.ToyLangException;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.Type.IType;

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

    @Override
    public IType typecheck(IGenericDictionary<String, IType> typeEnv) throws ToyLangException {
        return typeEnv.lookup(name);
    }
}