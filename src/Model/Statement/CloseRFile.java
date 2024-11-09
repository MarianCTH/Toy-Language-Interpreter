package Model.Statement;

import Model.Expression.IExpression;
import Model.State.PrgState;
import Model.Value.IValue;
import Exception.ToyLangException;
import Model.Value.Type.StringType;


public class CloseRFile implements IStatement{
    IExpression exp;

    public CloseRFile(IExpression exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        IValue value = this.exp.evaluate(state);

        if (!(value.getType() instanceof StringType))
            throw new ToyLangException("Filename did not evaluate to string");

        return null;
    }

    @Override
    public String toString() {
        return "closeRFile(" + exp.toString() + ")";
    }
}
