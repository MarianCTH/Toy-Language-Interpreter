package Model.Statement;

import ADT.Dictionary.IGenericDictionary;
import Exception.ToyLangException;
import Model.State.PrgState;
import Model.Value.Type.IType;

public interface IStatement {
    PrgState execute(PrgState state) throws ToyLangException;

    String toString();

    IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws ToyLangException;

}