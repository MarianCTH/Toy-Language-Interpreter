package Model.Statement;

import Exception.ToyLangException;
import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.Type.BoolType;

public class IfStmt implements IStatement {
    IExpression expression;
    IStatement left;
    IStatement right;

    public IfStmt(IExpression expression, IStatement left, IStatement right) {
        this.expression = expression;
        this.left = left;
        this.right = right;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        IValue value = expression.evaluate(state);
        if (!(value.getType() instanceof BoolType))
            throw new ToyLangException("Invalid expression value for if statement");

        if (((BoolValue) value).getValue())
            state.getExeStack().push(left);
        else
            state.getExeStack().push(right);
        return state;
    }

    @Override
    public String toString() {
        return "if(" + this.expression.toString() + ")" + "then {" + this.left.toString() + "} else {" + this.right.toString() + "}";
    }
}