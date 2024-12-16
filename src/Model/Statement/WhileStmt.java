package Model.Statement;

import ADT.Dictionary.IGenericDictionary;
import Exception.ToyLangException;
import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.*;
import Model.Value.Type.*;

public class WhileStmt implements IStatement{
    IExpression condition;
    IStatement statement;

    public WhileStmt(IExpression condition, IStatement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        IValue value = this.condition.evaluate(state);
        if (!(value.getType() instanceof BoolType)) {
            throw new ToyLangException("While condition should evaluate to a BooleanType");
        }
        if (((BoolValue) value).getValue()) {
            state.getExeStack().push(this);
            state.getExeStack().push(statement);
        }
        return null;
    }

    @Override
    public String toString() {
        return "While(" + condition.toString() + "){" + statement.toString() + "}";
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws ToyLangException {
        IType typexp=condition.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            return statement.typecheck(typeEnv);
        }
        else throw new ToyLangException("The condition of WHILE has not the type bool");
    }
}
