package Model.Statement;

import Exception.ToyLangException;
import Model.State.PrgState;
import Model.Value.Type.IType;

public class VarDeclStmt implements IStatement{
    String name;
    IType type;

    public VarDeclStmt(String name, IType type) {
        this.name = name;
        this.type = type;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLangException {
        state.getSymTable().declValue(name, type);
        return null;
    }

    @Override
    public String toString(){
        return type.toString() + " " + name;
    }
}