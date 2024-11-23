package Model.Statement;

import Model.Expression.IExpression;
import Model.State.PrgState;
import Exception.ToyLangException;

public class ReadHeap implements IStatement{
    IExpression expr;

    public ReadHeap(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        expr.evaluate(state);
        return null;
    }

    @Override
    public String toString() {
        return expr.toString();
    }
}
