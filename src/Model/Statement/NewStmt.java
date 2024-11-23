package Model.Statement;

import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.RefValue;
import Exception.ToyLangException;

public class NewStmt implements IStatement{
    String varName;
    IExpression expression;

    public NewStmt(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        IValue value = expression.evaluate(state);
        state.getSymTable().setValue(varName, new RefValue(state.getHeapTable().allocate(value), value.getType()));
        return null;
    }
    @Override
    public String toString() {
        return "new(" + varName + ", " + expression.toString() + ")";
    }
}
