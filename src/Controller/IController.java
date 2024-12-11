package Controller;

import Exception.ToyLangException;
import Model.State.PrgState;
import Model.Statement.IStatement;

import java.util.List;

public interface IController {
    void oneStepForAllPrg(List<PrgState> prgList) throws ToyLangException, InterruptedException;
    void allStep() throws ToyLangException, InterruptedException;
    void displayCurrentState() throws ToyLangException;

    void setDisplayFlag(boolean displayFlag);

    void setProgram(IStatement statement) throws ToyLangException;

    boolean getDisplayFlag();

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList);
}