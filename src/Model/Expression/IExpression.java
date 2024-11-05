package Model.Expression;

import Exception.ToyLangException;
import Model.State.PrgState;
import Model.Value.IValue;

public interface IExpression {
    IValue evaluate(PrgState state) throws ToyLangException;
    String toString();
}
