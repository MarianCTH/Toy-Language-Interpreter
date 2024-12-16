package Model.Statement;

import ADT.Dictionary.IGenericDictionary;
import Model.State.PrgState;
import Model.Value.Type.IType;
import Exception.ToyLangException;

public class CompStmt implements IStatement{
    IStatement firstStatement;
    IStatement secondStatement;

    public CompStmt(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public PrgState execute(PrgState state) {
        state.getExeStack().push(secondStatement);
        state.getExeStack().push(firstStatement);
        return null;
    }

    @Override
    public String toString(){
        return firstStatement + "; " + secondStatement;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws ToyLangException {
        return secondStatement.typecheck(firstStatement.typecheck(typeDictionary));
    }
}