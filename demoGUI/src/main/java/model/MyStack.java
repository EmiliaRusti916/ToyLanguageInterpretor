package model;

import statement.StatementInterface;

import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack<T> implements StackInterface<T>{

    //private final MyStack<T> exeStack;//= new MyStack<>();
    private final Stack<T> exeStack = new Stack<>();

    public MyStack() {
        //this.exeStack = new MyStack<>();
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.exeStack.isEmpty()) {
            throw new EmptyStackException();
        } else {
            return this.exeStack.pop();
        }
    }

    @Override
    public void push(T element) {
        this.exeStack.push(element);
    }

    @Override
    public boolean isEmpty() {
        return exeStack.isEmpty();
    }

    @Override
    public Stack<T> getStack() {
        return this.exeStack;
    }

    @Override
    public String toString() {
        return exeStack +
                "\n";
    }
}
