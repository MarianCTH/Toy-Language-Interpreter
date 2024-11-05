package Model.State;

import Exception.ToyLangException;
import Model.Statement.IStatement;

public class PrgState {
    int id;
    static int nextId = 0;
    IExecutionStack executionStack;
    ISymTable symTable;
    IOutput output;


    public PrgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IStatement statement) {
        this.id = getId();
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.executionStack.push(statement);
    }

    public synchronized int getId() {
        nextId++;
        return nextId;
    }

    public IExecutionStack getExeStack() {
        return executionStack;
    }

    public ISymTable getSymTable() {
        return symTable;
    }

    public IOutput getOutput() {
        return output;
    }

    public boolean isNotCompleted() {
        return this.executionStack.size() > 0;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "\n" + this.executionStack.toString().strip() + "\n" + this.symTable.toString().strip() + "\n" + this.output.toString().strip() + "\n";
    }

    public PrgState executeOneStep() throws ToyLangException {
        IStatement statement = executionStack.pop();
        return statement.execute(this);
    }
}