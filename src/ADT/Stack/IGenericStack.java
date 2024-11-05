package ADT.Stack;

import Exception.StackEmptyException;

public interface IGenericStack<T> {
    void push(T element);

    boolean isEmpty();

    int size();

    T top() throws StackEmptyException;

    T pop() throws StackEmptyException;
}
