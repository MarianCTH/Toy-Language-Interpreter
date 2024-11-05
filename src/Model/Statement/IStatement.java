package Model.Statement;

import Exception.ToyLangException;
import Model.State.PrgState;

public interface IStatement {
    PrgState execute(PrgState state) throws ToyLangException;

    String toString();
}