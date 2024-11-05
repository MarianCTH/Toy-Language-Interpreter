package Model.State;

import ADT.Stack.GenericStack;
import ADT.Stack.IGenericStack;
import Exception.StackEmptyException;
import Exception.ToyLangException;
import Model.Statement.IStatement;

public class ExecutionStack implements IExecutionStack {
    IGenericStack<IStatement> stack;

    public ExecutionStack() {
        this.stack = new GenericStack<>();
    }

    @Override
    public IStatement pop() throws StackEmptyException {
        return stack.pop();
    }

    @Override
    public void push(IStatement statement) {
        stack.push(statement);
    }

    @Override
    public boolean empty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder("Execution stack:\n");
        IGenericStack<IStatement> tmpStack = new GenericStack<>();
        try {
            while (!stack.isEmpty()) {
                tmpStack.push(stack.pop());
                answer.append(tmpStack.top().toString()).append('\n');
            }
            while (!tmpStack.isEmpty()) {
                stack.push(tmpStack.pop());
            }
        } catch (ToyLangException exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return answer.toString();
    }
}