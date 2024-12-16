package Model.Statement;

import ADT.Dictionary.IGenericDictionary;
import Exception.ToyLangException;
import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.Type.BoolType;
import Model.Value.Type.IType;
import com.sun.jdi.BooleanType;

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

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws ToyLangException {
        IType typexp=expression.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            left.typecheck(typeEnv.copy());
            right.typecheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new ToyLangException("The condition of IF has not the type bool");
    }
}