package model;

import statement.StatementInterface;

import java.util.Stack;

public interface StackInterface<T> {
    T pop();
    void push(T element);
    boolean isEmpty();

    Stack<T> getStack();

}
