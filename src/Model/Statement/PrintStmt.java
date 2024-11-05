package Model.Statement;

import Exception.ToyLangException;
import Model.Expression.IExpression;
import Model.State.PrgState;

public class PrintStmt implements IStatement {

    IExpression expression;

    public PrintStmt(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        state.getOutput().appendToOutput(expression.evaluate(state).toString());
        return null;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}