package Model.Statement;

import ADT.Dictionary.IGenericDictionary;
import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.Type.IType;
import Model.Value.Type.StringType;
import Model.Value.StringValue;

import Exception.ToyLangException;

public class OpenRFile implements IStatement{
    IExpression exp;

    public OpenRFile(IExpression exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        IValue value = this.exp.evaluate(state);

        if (!(value.getType() instanceof StringType))
            throw new ToyLangException("Filename did not evaluate to string");

        state.getFileTable().openFile(((StringValue) value).getValue());

        return state;
    }

    @Override
    public String toString() {
        return "openRFile(" + this.exp.toString() + ")";
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws ToyLangException {
        if ((new StringType()).equals(exp.typecheck(typeDictionary))) {
            return typeDictionary;
        }
        throw new ToyLangException("Open file expression doesn't evaluate to a StringType");
    }
}
