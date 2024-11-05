package Model.Statement;

import Model.State.PrgState;

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
}