package Controller;

import Exception.ToyLangException;
import Model.Statement.IStatement;

public interface IController {
    void executeOneStep() throws ToyLangException;

    void executeAllSteps() throws ToyLangException;

    void displayCurrentState() throws ToyLangException;

    void setDisplayFlag(boolean displayFlag);

    void setProgram(IStatement statement) throws ToyLangException;

    boolean getDisplayFlag();
}