package Model.Expression;

import Exception.ToyLangException;
import Model.State.PrgState;
import Model.Value.IValue;

public class ArithExp implements IExpression {
    IExpression left;
    IExpression right;
    String operator;

    public ArithExp(IExpression left, IExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public ArithExp(String operator, IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public ArithExp(char operator, IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
        this.operator = String.valueOf(operator);
    }


    @Override
    public IValue evaluate(PrgState state) throws ToyLangException {
        return left.evaluate(state).compose(right.evaluate(state), operator);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }
}