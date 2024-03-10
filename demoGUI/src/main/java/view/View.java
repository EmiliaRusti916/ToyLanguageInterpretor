package view;

import controller.Controller;
import exceptions.MyException;
import expressions.ArthExp;
import expressions.LogicExp;
import expressions.ValExp;
import expressions.VarExp;
import model.*;
import program.ProgramState;
import repository.Repository;
import repository.RepositoryInterface;
import statement.*;
import type.BoolType;
import type.IntType;
import type.StringType;
import value.BoolValue;
import value.IntValue;
import value.StringValue;
import value.ValueInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class View {
    private String filename;
/*
    public View(String filename) {
        this.filename = filename;
    }

    public void menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the wanted program(1/2/3/4): ");
        int option = scanner.nextInt();
        if(option == 1)
        {
            StatementInterface exp1 = new CompStmt(new VarDeclStmt("v",new IntType()), new CompStmt(new AssignStmt("v", new ValExp(new IntValue(2))), new PrintStmt(new LogicExp(new ValExp(new BoolValue(true)),new ValExp(new BoolValue(false)),2))));
            StackInterface<StatementInterface> stk = new MyStack<StatementInterface>();
            MapInterface<String, ValueInterface> symTbl = new MyMap<String, ValueInterface>();
            HeapInterface<Integer, ValueInterface> heap = new MyHeap<>();
            FileTableInterface<String, BufferedReader> fileTbl = new FileTable<>();
            ListInterface<ValueInterface> list = new MyList<ValueInterface>();
            stk.push(exp1);
            System.out.println(exp1.toString());
            ProgramState state = new ProgramState(stk, symTbl, list, fileTbl, heap);
            RepositoryInterface repository = new Repository(this.filename);
            repository.addProgram(state);
            Controller controller = new Controller(repository);
            try{
                controller.allSteps();
            }catch(MyException | IOException e){
                System.out.println(e.getMessage());
            }

        } else if (option==2) {
            StatementInterface exp2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                    new CompStmt(new VarDeclStmt("b",new IntType()),
                            new CompStmt(new AssignStmt("a", new ArthExp(new ValExp(new IntValue(2)),
                                    new ArthExp(new ValExp(new IntValue(3)), new ValExp(new IntValue(5)),3),1)),
                                        new CompStmt(new AssignStmt("b",new ArthExp(new VarExp("a"),
                                            new ValExp(new IntValue(1)),1)), new PrintStmt(new VarExp("b"))))));
            StackInterface<StatementInterface> stk = new MyStack<StatementInterface>();
            MapInterface<String, ValueInterface> symTbl = new MyMap<String, ValueInterface>();
            FileTableInterface<String, BufferedReader> fileTbl = new FileTable<>();
            ListInterface<ValueInterface> list = new MyList<ValueInterface>();
            HeapInterface<Integer, ValueInterface> heap = new MyHeap<>();
            stk.push(exp2);
            System.out.println(exp2.toString());
            ProgramState state = new ProgramState(stk, symTbl, list, fileTbl, heap);
            RepositoryInterface repository = new Repository(this.filename);
            repository.addProgram(state);
            Controller controller = new Controller(repository);
            try{
                controller.allSteps();
            }catch(MyException e){
                System.out.println(e.getMessage());
            }catch(IOException io)
            {
                System.out.println(io.getMessage());
            }

        } else if (option == 3) {
            StatementInterface exp3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValExp(new BoolValue(true))),
                    new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValExp(new IntValue(2))),
                            new AssignStmt("v", new ValExp(new IntValue(3)))),new PrintStmt(new VarExp("v"))))));
            StackInterface<StatementInterface> stk = new MyStack<StatementInterface>();
            MapInterface<String, ValueInterface> symTbl = new MyMap<String, ValueInterface>();
            ListInterface<ValueInterface> list = new MyList<ValueInterface>();
            FileTableInterface<String, BufferedReader> fileTbl = new FileTable<>();
            HeapInterface<Integer, ValueInterface> heap = new MyHeap<>();
            stk.push(exp3);
            System.out.println(exp3.toString());
            ProgramState state = new ProgramState(stk, symTbl, list,fileTbl, heap);
            RepositoryInterface repository = new Repository(this.filename);
            repository.addProgram(state);
            Controller controller = new Controller(repository);
            try{
                controller.allSteps();
            }catch(MyException | IOException e){
                System.out.println(e.getMessage());
            }

        } else if (option == 4) {
            StatementInterface declaringV = new VarDeclStmt("v", new StringType());
            StatementInterface assigningV = new AssignStmt("v", new ValExp(new StringValue("./input/test.in")));
            StatementInterface openingFile = new OpenRFileStatement(new VarExp("v"));
            StatementInterface declaringC = new VarDeclStmt("c", new IntType());
            StatementInterface readingC = new ReadFileStatement(new VarExp("v"), "c");
            StatementInterface printingC = new PrintStmt(new VarExp("c"));
            StatementInterface closingFile = new CloseRFileStatement(new VarExp("v"));
            StatementInterface stm = new CompStmt(declaringV, new CompStmt(assigningV, new CompStmt(openingFile,
                    new CompStmt(declaringC, new CompStmt(readingC, new CompStmt(printingC,
                            new CompStmt(readingC, new CompStmt(printingC, closingFile))))))));

            StackInterface<StatementInterface> stk = new MyStack<StatementInterface>();
            MapInterface<String, ValueInterface> symTbl = new MyMap<String, ValueInterface>();
            ListInterface<ValueInterface> list = new MyList<ValueInterface>();
            FileTableInterface<String, BufferedReader> fileTbl = new FileTable<>();
            HeapInterface<Integer, ValueInterface> heap = new MyHeap<>();
            stk.push(stm);
            System.out.println(stm.toString());
            ProgramState state = new ProgramState(stk, symTbl, list,fileTbl, heap);
            RepositoryInterface repository = new Repository(this.filename);
            repository.addProgram(state);
            Controller controller = new Controller(repository);
            try{
                controller.allSteps();
            }catch(MyException | IOException e){
                System.out.println(e.getMessage());
            }

        }

    }
    */
}
