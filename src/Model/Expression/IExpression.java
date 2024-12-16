package Model.Expression;

import ADT.Dictionary.IGenericDictionary;
import Exception.ToyLangException;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.Type.IType;

public interface IExpression {
    IValue evaluate(PrgState state) throws ToyLangException;
    String toString();
    IType typecheck(IGenericDictionary<String, IType> typeEnv) throws ToyLangException;
}
