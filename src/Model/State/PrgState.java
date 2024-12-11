package Model.State;

import Exception.ToyLangException;
import Model.Statement.IStatement;

public class PrgState {
    int id;
    IExecutionStack executionStack;
    ISymTable symTable;
    IOutput output;
    IFileTable fileTable;
    IHeapTable heapTable;
    static int nextId = 0;

    public PrgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IStatement statement, IFileTable fileTable, IHeapTable heap) {
        this.id = getId();
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.executionStack.push(statement);
        this.fileTable = fileTable;
        this.heapTable = heap;
    }

    public IExecutionStack getExeStack() {
        return executionStack;
    }

    public synchronized int getId() {
        nextId++;
        return nextId;
    }

    public ISymTable getSymTable() {
        return symTable;
    }

    public IOutput getOutput() {
        return output;
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public IHeapTable getHeapTable() {
        return heapTable;
    }

    public boolean isNotCompleted() {
        return this.executionStack.size() > 0;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "\n" +
                this.executionStack.toString().strip() + "\n" +
                this.symTable.toString().strip() + "\n" +
                this.output.toString().strip() + "\n" +
                this.fileTable.toString().strip() + "\n" +
                this.heapTable.toString() + "\n";
    }

    public PrgState oneStep() throws ToyLangException {
        if(executionStack.empty())
            throw new ToyLangException("Prgstate stack is empty");

        IStatement crtStmt = executionStack.pop();
        return crtStmt.execute(this);
    }
}