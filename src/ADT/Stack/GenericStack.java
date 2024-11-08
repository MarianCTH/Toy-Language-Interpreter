package ADT.Stack;

import java.util.Stack;
import Exception.StackEmptyException;

public class GenericStack<T> implements IGenericStack<T> {
    Stack<T> stack;

    public GenericStack() {
        this.stack = new Stack<>();
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    @Override
    public T pop() throws StackEmptyException {
        if (this.stack.isEmpty())
            throw new StackEmptyException("Stack is empty");
        return this.stack.pop();
    }

    @Override
    public T top() throws StackEmptyException {
        if (this.stack.isEmpty())
            throw new StackEmptyException("Stack is empty");
        return this.stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (T element : this.stack) {
            result.append(element.toString()).append("\n");
        }
        return "GenericStack{" +
                "stack = " + result +
                '}';
    }

    public Stack<T> getStack() {
        return stack;
    }

    public void setStack(Stack<T> stack) {
        this.stack = stack;
    }
}
