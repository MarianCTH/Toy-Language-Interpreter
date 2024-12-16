package Model.Statement;

import ADT.Dictionary.IGenericDictionary;
import Exception.ToyLangException;
import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.Type.IType;

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

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws ToyLangException {
        IType variableType = typeDictionary.lookup(variableName);
        IType expressionType = expression.typecheck(typeDictionary);

        if (variableType.equals(expressionType))
            return typeDictionary;
        else
            throw new ToyLangException("Assignment: right hand side and left hand side have different types ");
    }
}