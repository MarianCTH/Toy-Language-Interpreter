package Controller;

import Exception.ToyLangException;
import Model.State.*;
import Model.Statement.IStatement;
import Repository.IRepository;

public class Controller implements IController {
    IRepository repository;
    boolean displayFlag;

    public boolean getDisplayFlag() {
        return displayFlag;
    }

    @Override
    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public Controller(IRepository repository, boolean displayFlag) {
        this.repository = repository;
        this.displayFlag = displayFlag;
    }

    @Override
    public void executeOneStep() throws ToyLangException {
        PrgState state = repository.getCrtPrg();
        IStatement currentStatement = state.getExeStack().pop();
        currentStatement.execute(state);

        if (this.displayFlag)
            this.displayCurrentState();
    }

    @Override
    public void executeAllSteps() throws ToyLangException {
        while (true) {
            if (this.repository.getProgramsList().isEmpty()) {
                break;
            }
            this.executeOneStep();
        }
    }

    @Override
    public void displayCurrentState() throws ToyLangException {
        this.repository.getProgramsList().forEach(program -> System.out.println(program.toString() + "\n"));
    }

    @Override
    public void setProgram(IStatement statement) throws ToyLangException {
        this.repository.clear();
        this.repository.add(new PrgState(new ExecutionStack(), new SymTable(), new Output(), statement));
        if (this.displayFlag) {
            this.displayCurrentState();
        }
    }
}