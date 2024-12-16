package Model.Expression;

import ADT.Dictionary.IGenericDictionary;
import Exception.ToyLangException;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.Type.BoolType;
import Model.Value.Type.IType;
import Model.Value.Type.IntType;

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

    @Override
    public IType typecheck(IGenericDictionary<String, IType> typeDictionary) throws ToyLangException {
        IType firstType = left.typecheck(typeDictionary);
        IType secondType = right.typecheck(typeDictionary);

        if (firstType == null || !firstType.equals(secondType)) {
            throw new ToyLangException("Binary expression operands are not the same");
        }

        return switch (operator) {
            case "+", "-", "*", "/" -> new IntType();
            case "<", "<=", ">", ">=", "==", "!=" -> new BoolType();
            default -> firstType;
        };
    }
}