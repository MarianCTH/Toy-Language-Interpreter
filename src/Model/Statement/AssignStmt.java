package Model.Statement;

import Exception.ToyLangException;
import Model.Expression.IExpression;
import Model.State.PrgState;

public class AssignStmt implements IStatement {
    String variableName;
    IExpression expression;

    public AssignStmt(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        state.getSymTable().setValue(variableName, expression.evaluate(state));
        return null;
    }

    @Override
    public String toString() {
        return variableName + " = " + expression.toString();
    }
}