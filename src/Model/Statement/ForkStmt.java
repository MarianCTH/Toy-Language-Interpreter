package Model.Statement;

import ADT.Dictionary.IGenericDictionary;
import Model.State.ExecutionStack;
import Model.State.PrgState;
import Exception.ToyLangException;
import Model.Value.Type.IType;

public class ForkStmt implements IStatement {
    IStatement innerStatement;

    public ForkStmt(IStatement innerStatement) {
        this.innerStatement = innerStatement;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        return new PrgState(new ExecutionStack(), state.getSymTable().copy(), state.getOutput(), innerStatement, state.getFileTable(), state.getHeapTable());
    }

    @Override
    public String toString() {
        return "fork(" + innerStatement.toString() + ")";
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws ToyLangException {
        return innerStatement.typecheck(typeDictionary);
    }
}