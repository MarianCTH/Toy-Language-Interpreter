package Model.State;

import Exception.StackEmptyException;
import Model.Statement.IStatement;
import java.util.List;

public interface IExecutionStack {
    IStatement pop() throws StackEmptyException;

    void push(IStatement statement);

    boolean empty();

    int size();

    String toString();

    List<String> getStackAsStrings();
}