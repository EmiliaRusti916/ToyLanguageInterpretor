package program;

import exceptions.MyException;
import model.*;
import statement.StatementInterface;
import value.StringValue;
import value.ValueInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ProgramState {
    StackInterface<StatementInterface> exeStack;
    MapInterface<String, ValueInterface> symMap;
    ListInterface<ValueInterface> out;
    FileTableInterface<String, BufferedReader> fileTbl;
    HeapInterface<Integer, ValueInterface> heap;
    private static int id;
    private int currentID;

    public FileTableInterface<String, BufferedReader> getFileTbl() {
        return fileTbl;
    }

    public ProgramState(StackInterface<StatementInterface> e, MapInterface<String, ValueInterface> s, ListInterface<ValueInterface> o, FileTableInterface<String, BufferedReader> fi, HeapInterface<Integer, ValueInterface> h) {
        this.exeStack = e;
        this.symMap = s;
        this.out = o;
        this.fileTbl = fi;
        this.heap = h;
        this.currentID = newID();

    }

    public int getId() {
        return currentID;
    }

    public static  synchronized int newID(){
        int newId=id+1;
        id++;
        return newId;
    }

    public HeapInterface<Integer, ValueInterface> getHeap() { return this.heap; }

    @Override
    public String toString() {
        return "Program ID: " +currentID+ "\n" + "Stack: \n" + exeStack.toString() +
                 "SymbolTable: \n "+symMap.toString() + "List: \n" + out.toString() + "FileTable: \n "+fileTbl.getOnlyKey() + heap.toString() + "\n";
    }

    public StackInterface<StatementInterface> getStack(){
        return this.exeStack;
    }
    public  MapInterface<String, ValueInterface> getMap() { return this.symMap;}

    public ListInterface<ValueInterface> getOut() {
        return this.out;
    }
    public boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }
    public ProgramState oneStep() throws MyException, IOException {
        if(this.exeStack.isEmpty())
            throw new MyException("Program state is empty!");
        StatementInterface currentStmt = this.exeStack.pop();
        return currentStmt.execute(this);
    }
}
