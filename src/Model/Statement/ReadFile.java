package Model.Statement;

import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.IValue;
import Model.Value.Type.StringType;
import Model.Value.StringValue;
import Model.Value.IntValue;

import Exception.ToyLangException;

public class ReadFile implements IStatement{
    IExpression exp;
    String var_name;

    public ReadFile(IExpression exp, String var_name){
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        IValue value = this.exp.evaluate(state);
        if (!(value.getType() instanceof StringType))
            throw new ToyLangException("Filename did not evaluate to string");

        state.getSymTable().setValue(var_name, new IntValue(state.getFileTable().readFile(((StringValue) value).getValue())));

        return null;
    }

    @Override
    public String toString() {
        return "readFile(" + this.exp.toString() + ", " + this.var_name + ")";
    }
}
