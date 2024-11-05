package Model.State;

import Exception.StackEmptyException;
import Model.Statement.IStatement;

public interface IExecutionStack {
    IStatement pop() throws StackEmptyException;

    void push(IStatement statement);

    boolean empty();

    int size();

    String toString();
}