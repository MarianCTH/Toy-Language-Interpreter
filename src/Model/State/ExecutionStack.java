package Model.State;

import ADT.Stack.GenericStack;
import ADT.Stack.IGenericStack;
import Exception.StackEmptyException;
import Exception.ToyLangException;
import Model.Statement.IStatement;

import java.util.ArrayList;
import java.util.List;

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
        StringBuilder answer = new StringBuilder("ExeStack:\n");
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

    @Override
    public List<String> getStackAsStrings() {
        List<String> stackStrings = new ArrayList<>();
        IGenericStack<IStatement> tmpStack = new GenericStack<>();
        try {
            // Temporarily store the stack items in the temporary stack and add them to the list
            while (!stack.isEmpty()) {
                tmpStack.push(stack.pop());
            }
            // Now add the elements from tmpStack to the list
            while (!tmpStack.isEmpty()) {
                stackStrings.add(tmpStack.top().toString());
                stack.push(tmpStack.pop());  // Restore the stack back to the original state
            }
        } catch (ToyLangException exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return stackStrings;
    }
}