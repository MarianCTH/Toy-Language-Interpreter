package Model.Statement;

import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.RefValue;
import Exception.ToyLangException;
import Model.Value.Type.RefType;

import java.util.Map;

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

        Map<String, IValue> symTableContent = state.getSymTable().getContent();

        if (!symTableContent.containsKey(varName)) {
            throw new ToyLangException("Variable " + varName + " is not defined in the Symbol Table.");
        }

        IValue varValue = symTableContent.get(varName);
        if (!(varValue.getType() instanceof RefType refType)) {
            throw new ToyLangException("Variable " + varName + " is not of RefType.");
        }

        if (!value.getType().equals(refType.getInner())) {
            throw new ToyLangException("Type of the evaluated expression does not match the location type of " + varName + ".");
        }

        state.getSymTable().setValue(varName,
                                    new RefValue(state.getHeapTable().allocate(value), value.getType()));
        return null;
    }
    @Override
    public String toString() {
        return "new(" + varName + ", " + expression.toString() + ")";
    }
}
