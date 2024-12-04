package Model.State;

import Exception.ToyLangException;
import Model.Statement.IStatement;

public class PrgState {
    IExecutionStack executionStack;
    ISymTable symTable;
    IOutput output;
    IFileTable fileTable;
    IHeapTable heapTable;

    public PrgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IStatement statement, IFileTable fileTable) {
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.executionStack.push(statement);
        this.fileTable = fileTable;
        this.heapTable = new HeapTable();
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

    public IFileTable getFileTable() {
        return fileTable;
    }

    public IHeapTable getHeapTable() {
        return heapTable;
    }

    @Override
    public String toString() {
        return this.executionStack.toString().strip() + "\n" +
                this.symTable.toString().strip() + "\n" +
                this.output.toString().strip() + "\n" +
                this.fileTable.toString().strip() + "\n" +
                this.heapTable.toString() + "\n";
    }
}