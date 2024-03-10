package com.example.demogui;

import exceptions.MyException;
import expressions.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.*;
import program.ProgramState;
import repository.RepositoryInterface;
import repository.Repository;
import statement.*;
import type.*;
import value.BoolValue;
import value.IntValue;
import value.StringValue;
import value.ValueInterface;
import view.textInterface.commands.Command;
import view.textInterface.commands.ExitCommand;
import view.textInterface.commands.RunExampleCommand;

import java.io.BufferedReader;

public class DisplayListController {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<RunExampleCommand> list_of_istmt;

    public ListView<RunExampleCommand> getList_of_istmt() {
        return list_of_istmt;
    }

    @FXML
    public void initialize() {
        list_of_istmt.setCellFactory(TextFieldListCell.forListView(new StringConverter<RunExampleCommand>() {
            @Override
            public String toString(RunExampleCommand runExampleCommand) {
                return runExampleCommand.toString();
            }

            @Override
            public RunExampleCommand fromString(String s) {
                return null;
            }
        }));

        list_of_istmt.setItems(getExamples());
        list_of_istmt.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list_of_istmt.getSelectionModel().selectIndices(1);
        list_of_istmt.getFocusModel().focus(2);
        list_of_istmt.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
    private ObservableList<RunExampleCommand> getExamples() {
        ObservableList<RunExampleCommand> examples = FXCollections.observableArrayList();
        StatementInterface exp1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        //StatementInterface exp1 = new CompStmt(new VarDeclStmt("v",new IntType()), new CompStmt(new AssignStmt("v", new ValExp(new IntValue(2))), new PrintStmt(new LogicExp(new ValExp(new BoolValue(true)),new ValExp(new BoolValue(false)),2))));
        StackInterface<StatementInterface> stk = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl = new MyMap<String, ValueInterface>();
        FileTableInterface<String, BufferedReader> fileTbl = new FileTable<>();
        HeapInterface<Integer, ValueInterface> heap = new MyHeap<>();
        ListInterface<ValueInterface> list = new MyList<ValueInterface>();
        MapInterface<String, TypeInterface> typeEnv = new MyMap<>();
        stk.push(exp1);
        RunExampleCommand runEx1 = null;
        try {
            exp1.typeCheck(typeEnv);
            ProgramState state = new ProgramState(stk, symTbl, list, fileTbl, heap);
            RepositoryInterface repository = new Repository("exp1.txt");
            repository.addProgram(state);
            Controller controller = new Controller(repository);
            runEx1 = new RunExampleCommand("1", exp1.toString(), controller);
            examples.add(runEx1);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 1!");
            a.show();
        }
        StatementInterface exp2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArthExp(new ValExp(new IntValue(2)),
                                new ArthExp(new ValExp(new IntValue(1)), new ValExp(new IntValue(5)), 3), 1)),
                                new CompStmt(new AssignStmt("b", new ArthExp(new VarExp("a"),
                                        new ValExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        StackInterface<StatementInterface> stk2 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl2 = new MyMap<String, ValueInterface>();
        FileTableInterface<String, BufferedReader> fileTbl2 = new FileTable<>();
        ListInterface<ValueInterface> list2 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap2 = new MyHeap<>();
        MapInterface<String, TypeInterface> typeEnv2 = new MyMap<>();
        stk2.push(exp2);
        RunExampleCommand runEx2 = null;
        try {
            exp2.typeCheck(typeEnv2);
            ProgramState state2 = new ProgramState(stk2, symTbl2, list2, fileTbl2, heap2);
            RepositoryInterface repository2 = new Repository("exp2.txt");
            repository2.addProgram(state2);
            Controller controller2 = new Controller(repository2);
            runEx2 = new RunExampleCommand("2", exp2.toString(), controller2);
            examples.add(runEx2);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 2!");
            a.show();
        }
        StatementInterface exp3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("a", new ValExp(new BoolValue(true))),
                        new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValExp(new IntValue(2))),
                                new AssignStmt("v", new ValExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        StackInterface<StatementInterface> stk3 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl3 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list3 = new MyList<ValueInterface>();
        FileTableInterface<String, BufferedReader> fileTbl3 = new FileTable<>();
        HeapInterface<Integer, ValueInterface> heap3 = new MyHeap<>();
        MapInterface<String, TypeInterface> typeEnv3 = new MyMap<>();
        stk3.push(exp3);
        RunExampleCommand runEx3 = null;
        try {
            exp3.typeCheck(typeEnv3);
            ProgramState state3 = new ProgramState(stk3, symTbl3, list3, fileTbl3, heap3);
            RepositoryInterface repository3 = new Repository("exp3.txt");
            repository3.addProgram(state3);
            Controller controller3 = new Controller(repository3);
            runEx3 = new RunExampleCommand("3", exp3.toString(), controller3);
            examples.add(runEx3);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 3!");
            a.show();
        }
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

        StackInterface<StatementInterface> stk4 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl4 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list4 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap4 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl4 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv4 = new MyMap<>();
        stk4.push(stm);
        RunExampleCommand runEx4 = null;
        try {
            stm.typeCheck(typeEnv4);
            ProgramState state4 = new ProgramState(stk4, symTbl4, list4, fileTbl4, heap4);
            RepositoryInterface repository4 = new Repository("exp4.txt");
            repository4.addProgram(state4);
            Controller controller4 = new Controller(repository4);
            runEx4 = new RunExampleCommand("4", stm.toString(), controller4);
            examples.add(runEx4);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 4!");
            a.show();
        }
        StatementInterface refDeclaration = new VarDeclStmt("v", new RefType(new IntType()));
        StatementInterface alloc = new HeapAllocationStmt("v", new ValExp(new IntValue(20)));
        StatementInterface alloca = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        StatementInterface vina = new NopStmt();
        StatementInterface alloc30 = new HeapAllocationStmt("v", new ValExp(new IntValue(30)));
        StatementInterface print1 = new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a"))));
        StatementInterface exp5 = new CompStmt(refDeclaration, new CompStmt(alloc, new CompStmt(alloca, new CompStmt(vina, new CompStmt(alloc30, print1)))));
        StackInterface<StatementInterface> stk5 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl5 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list5 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap5 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl5 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv5 = new MyMap<>();
        stk5.push(exp5);
        RunExampleCommand runEx5 = null;
        try {
            exp5.typeCheck(typeEnv5);
            ProgramState state5 = new ProgramState(stk5, symTbl5, list5, fileTbl5, heap5);
            RepositoryInterface repository5 = new Repository("exp5.txt");
            repository5.addProgram(state5);
            Controller controller5 = new Controller(repository5);
            runEx5 = new RunExampleCommand("5", exp5.toString(), controller5);
            examples.add(runEx5);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 5!");
            a.show();
        }
        StatementInterface exp6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStmt("v", new ValExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocationStmt("v", new ValExp(new IntValue(30))),
                                        new CompStmt(new HeapAllocationStmt("a", new VarExp("v")),
                                                new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))));
        StackInterface<StatementInterface> stk6 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl6 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list6 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap6 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl6 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv6 = new MyMap<>();
        stk6.push(exp6);
        RunExampleCommand runEx6 = null;
        try {
            exp6.typeCheck(typeEnv6);
            ProgramState state6 = new ProgramState(stk6, symTbl6, list6, fileTbl6, heap6);
            RepositoryInterface repository6 = new Repository("exp6.txt");
            repository6.addProgram(state6);
            Controller controller6 = new Controller(repository6);
            runEx6 = new RunExampleCommand("6", exp6.toString(), controller6);
            examples.add(runEx6);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 6!");
            a.show();
        }
        StatementInterface exp7 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValExp(new IntValue(0)), 5), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArthExp(new VarExp("v"), new ValExp(new IntValue(1)), 2)))),
                                new PrintStmt(new VarExp("v")))));
        StackInterface<StatementInterface> stk7 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl7 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list7 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap7 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl7 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv7 = new MyMap<>();
        stk7.push(exp7);
        RunExampleCommand runEx7 = null;
        try {
            exp7.typeCheck(typeEnv7);
            ProgramState state7 = new ProgramState(stk7, symTbl7, list7, fileTbl7, heap7);
            RepositoryInterface repository7 = new Repository("exp7.txt");
            repository7.addProgram(state7);
            Controller controller7 = new Controller(repository7);
            runEx7 = new RunExampleCommand("7", exp7.toString(), controller7);
            examples.add(runEx7);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 7!");
            a.show();
        }

        StatementInterface varDecl = new VarDeclStmt("v", new IntType());
        StatementInterface refDecl = new VarDeclStmt("a", new RefType(new IntType()));
        StatementInterface assignDecl = new AssignStmt("v", new ValExp(new IntValue(10)));
        StatementInterface allocDecl = new HeapAllocationStmt("a", new ValExp(new IntValue(22)));
        StatementInterface wDeclFork = new HeapWritingStmt("a", new ValExp(new IntValue(30)));
        StatementInterface assignDeclFork = new AssignStmt("v", new ValExp(new IntValue(32)));
        StatementInterface printFork = new PrintStmt(new VarExp("v"));
        StatementInterface readFork = new PrintStmt(new HeapReadExp(new VarExp("a")));
        StatementInterface forkStmt = new ForkStmt(new CompStmt(wDeclFork, new CompStmt(assignDeclFork, new CompStmt(printFork, readFork))));
        StatementInterface printStmt = new PrintStmt(new VarExp("v"));
        StatementInterface readStmt = new PrintStmt(new HeapReadExp(new VarExp("a")));
        StatementInterface exp8 = new CompStmt(varDecl, new CompStmt(refDecl, new CompStmt(assignDecl, new CompStmt(allocDecl, new CompStmt(forkStmt, new CompStmt(printStmt, readStmt))))));

        StackInterface<StatementInterface> stk8 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl8 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list8 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap8 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl8 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv8 = new MyMap<>();
        stk8.push(exp8);
        RunExampleCommand runEx8 = null;
        try {
            exp8.typeCheck(typeEnv8);
            ProgramState state8 = new ProgramState(stk8, symTbl8, list8, fileTbl8, heap8);
            RepositoryInterface repository8 = new Repository("exp8.txt");
            repository8.addProgram(state8);
            Controller controller8 = new Controller(repository8);
            runEx8 = new RunExampleCommand("8", exp8.toString(), controller8);
            examples.add(runEx8);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 8!");
            a.show();
        }

        StatementInterface exp9 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("counter", new IntType()),
                        new WhileStmt(new RelationalExp(new VarExp("counter"), new ValExp(new IntValue(10)), 1),
                                new CompStmt(new ForkStmt(new ForkStmt(new CompStmt(new HeapAllocationStmt("a", new VarExp("counter")),
                                        new PrintStmt(new HeapReadExp(new VarExp("a")))))), new AssignStmt("counter", new ArthExp(new VarExp("counter"), new ValExp(new IntValue(1)), 1))))));
        StackInterface<StatementInterface> stk9 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl9 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list9 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap9 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl9 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv9 = new MyMap<>();
        stk9.push(exp9);
        RunExampleCommand runEx9 = null;
        try {
            exp9.typeCheck(typeEnv9);
            ProgramState state9 = new ProgramState(stk9, symTbl9, list9, fileTbl9, heap9);
            RepositoryInterface repository9 = new Repository("exp9.txt");
            repository9.addProgram(state9);
            Controller controller9 = new Controller(repository9);
            runEx9 = new RunExampleCommand("9", exp9.toString(), controller9);
            examples.add(runEx9);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 9!");
            a.show();
        }


        //FOR
        StatementInterface exp10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntValue(20))),
                        new CompStmt(new ForStmt("v", new ValExp(new IntValue(0)),
                                new ValExp(new IntValue(3)),
                                new ArthExp(new VarExp("v"), new ValExp(new IntValue(1)), 1),
                                new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArthExp(new VarExp("v"), new ValExp(new IntValue(1)), 1))))),
                                new PrintStmt(new ArthExp(new VarExp("v"), new ValExp(new IntValue(10)), 3)))));
        StackInterface<StatementInterface> stk10 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl10 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list10 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap10 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl10 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv10 = new MyMap<>();
        stk10.push(exp10);
        RunExampleCommand runEx10 = null;
        try {
            exp10.typeCheck(typeEnv10);
            ProgramState state10 = new ProgramState(stk10, symTbl10, list10, fileTbl10, heap10);
            RepositoryInterface repository10 = new Repository("exp10.txt");
            repository10.addProgram(state10);
            Controller controller10 = new Controller(repository10);
            runEx10 = new RunExampleCommand("10", exp10.toString(), controller10);
            examples.add(runEx10);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 10!");
            a.show();
        }


        //SLEEP 1
        StatementInterface exp11 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntValue(0))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValExp(new IntValue(3)), 1),
                                new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArthExp(new VarExp("v"), new ValExp(new IntValue(1)), 1)))),
                                        new AssignStmt("v", new ArthExp(new VarExp("v"), new ValExp(new IntValue(1)), 1)))),
                                new CompStmt(new SleepStmt(5), new PrintStmt(new ArthExp(new VarExp("v"), new ValExp(new IntValue(10)), 3))))));
        StackInterface<StatementInterface> stk11 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl11 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list11 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap11 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl11 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv11 = new MyMap<>();
        stk11.push(exp11);
        RunExampleCommand runEx11 = null;
        try {
            exp11.typeCheck(typeEnv11);
            ProgramState state11 = new ProgramState(stk11, symTbl11, list11, fileTbl11, heap11);
            RepositoryInterface repository11 = new Repository("exp11.txt");
            repository11.addProgram(state11);
            Controller controller11 = new Controller(repository11);
            runEx11 = new RunExampleCommand("11", exp11.toString(), controller11);
            examples.add(runEx11);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 11!");
            a.show();
        }


        //WAIT
        StatementInterface exp12 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntValue(20))),
                        new CompStmt(new WaitStmt(10), new PrintStmt(new ArthExp(new VarExp("v"), new ValExp(new IntValue(10)), 3)))));
        StackInterface<StatementInterface> stk12 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl12 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list12 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap12 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl12 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv12 = new MyMap<>();
        stk12.push(exp12);
        RunExampleCommand runEx12 = null;
        try {
            exp12.typeCheck(typeEnv12);
            ProgramState state12 = new ProgramState(stk12, symTbl12, list12, fileTbl12, heap12);
            RepositoryInterface repository12 = new Repository("exp12.txt");
            repository12.addProgram(state12);
            Controller controller12 = new Controller(repository12);
            runEx12 = new RunExampleCommand("12", exp12.toString(), controller12);
            examples.add(runEx12);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 12!");
            a.show();
        }


        //MULEXP
        StatementInterface exp13 = new CompStmt(new VarDeclStmt("v1", new IntType()),
                new CompStmt(new VarDeclStmt("v2", new IntType()), new CompStmt(new AssignStmt("v1", new ValExp(new IntValue(2))),
                        new CompStmt(new AssignStmt("v2", new ValExp(new IntValue(3))),
                                new IfStmt(new RelationalExp(new VarExp("v1"), new ValExp(new IntValue(0)), 4),
                                        new PrintStmt(new MULExp(new VarExp("v1"), new VarExp("v2"))), new PrintStmt(new VarExp("v1")))))));
        StackInterface<StatementInterface> stk13 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl13 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list13 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap13 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl13 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv13 = new MyMap<>();
        stk13.push(exp13);
        RunExampleCommand runEx13 = null;
        try {
            exp13.typeCheck(typeEnv13);
            ProgramState state13 = new ProgramState(stk13, symTbl13, list13, fileTbl13, heap13);
            RepositoryInterface repository13 = new Repository("exp13.txt");
            repository13.addProgram(state13);
            Controller controller13 = new Controller(repository13);
            runEx13 = new RunExampleCommand("13", exp13.toString(), controller13);
            examples.add(runEx13);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 13!");
            a.show();
        }


        //SLEEP2 : v=10; fork(v=v-1;v=v-1;print(v));sleep(10);print(v*10);
        StatementInterface exp14 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntValue(10))),
                        new CompStmt(new ForkStmt(new PrintStmt(
                                new ArthExp(new VarExp("v"), new ValExp(new IntValue(2)), 2))),
                                new CompStmt(new SleepStmt(10), new PrintStmt(new ArthExp(new VarExp("v"), new ValExp(new IntValue(10)), 3))))));
        StackInterface<StatementInterface> stk14 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl14 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list14 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap14 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl14 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv14 = new MyMap<>();
        stk14.push(exp14);
        RunExampleCommand runEx14 = null;
        try {
            exp14.typeCheck(typeEnv14);
            ProgramState state14 = new ProgramState(stk14, symTbl14, list14, fileTbl14, heap14);
            RepositoryInterface repository14 = new Repository("exp14.txt");
            repository14.addProgram(state14);
            Controller controller14 = new Controller(repository14);
            runEx14 = new RunExampleCommand("14", exp14.toString(), controller14);
            examples.add(runEx14);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 14!");
            a.show();
        }


        //CONDITIONAL ASSIGNMENT : bool b;int c; b = true; c=b?100:200;
        //                                                  print(c);
        //                                                  c=false?100:200;
        //                                                  print(c);
        StatementInterface exp15 = new CompStmt(new VarDeclStmt("b", new BoolType()),
                new CompStmt(new VarDeclStmt("c", new IntType()),
                        new CompStmt(new AssignStmt("b", new ValExp(new BoolValue(true))),
                                new CompStmt(new ConditionalAssignStmt("c",
                                        new VarExp("b"),
                                        new ValExp(new IntValue(100)),
                                        new ValExp(new IntValue(200))),
                                        new CompStmt(new PrintStmt(new VarExp("c")),
                                                new CompStmt(new ConditionalAssignStmt("c",
                                                        new ValExp(new BoolValue(false)),
                                                        new ValExp(new IntValue(100)),
                                                        new ValExp(new IntValue(200))),
                                                        new PrintStmt(new VarExp("c"))))))));
        StackInterface<StatementInterface> stk15 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl15 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list15 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap15 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl15 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv15 = new MyMap<>();
        stk15.push(exp15);
        RunExampleCommand runEx15 = null;
        try {
            exp15.typeCheck(typeEnv15);
            ProgramState state15 = new ProgramState(stk15, symTbl15, list15, fileTbl15, heap15);
            RepositoryInterface repository15 = new Repository("exp15.txt");
            repository15.addProgram(state15);
            Controller controller15 = new Controller(repository15);
            runEx15 = new RunExampleCommand("15", exp15.toString(), controller15);
            examples.add(runEx15);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 15!");
            a.show();
        }


        //REPEAT UNTIL
        StatementInterface exp16 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntValue(0))),
                        new CompStmt(new RepeatUntilStmt(
                                new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArthExp(new VarExp("v"), new ValExp(new IntValue(1)), 2)))),
                                        new AssignStmt("v", new ArthExp(new VarExp("v"), new ValExp(new IntValue(1)), 1))),
                                new RelationalExp(new VarExp("v"), new ValExp(new IntValue(3)),3)
                        ),
                                new CompStmt(new VarDeclStmt("x", new IntType()),
                                        new CompStmt(new VarDeclStmt("y", new IntType()),
                                                new CompStmt(new VarDeclStmt("z", new IntType()),
                                                        new CompStmt(new VarDeclStmt("w", new IntType()),
                                                                new CompStmt(new AssignStmt("x", new ValExp(new IntValue(1))),
                                                                        new CompStmt(new AssignStmt("y", new ValExp(new IntValue(2))),
                                                                                new CompStmt(new AssignStmt("z", new ValExp(new IntValue(3))),
                                                                                        new CompStmt(new AssignStmt("w", new ValExp(new IntValue(4))),
                                                                                                new PrintStmt(new ArthExp(new VarExp("v"), new ValExp(new IntValue(10)),3)))))))))))));
        StackInterface<StatementInterface> stk16 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl16 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list16 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap16 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl16 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv16 = new MyMap<>();
        stk16.push(exp16);
        RunExampleCommand runEx16 = null;
        try {
            exp16.typeCheck(typeEnv16);
            ProgramState state16 = new ProgramState(stk16, symTbl16, list16, fileTbl16, heap16);
            RepositoryInterface repository16 = new Repository("exp16.txt");
            repository16.addProgram(state16);
            Controller controller16 = new Controller(repository16);
            runEx16 = new RunExampleCommand("16", exp16.toString(), controller16);
            examples.add(runEx16);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 16!");
            a.show();
        }

        //SWITCH
        StatementInterface exp17 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new VarDeclStmt("c", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValExp(new IntValue(1))),
                                        new CompStmt(new AssignStmt("b", new ValExp(new IntValue(2))),
                                                new CompStmt(new AssignStmt("c", new ValExp(new IntValue(5))),
                                                        new CompStmt(new SwitchStmt
                                                                (new ArthExp(new VarExp("a"), new ValExp(new IntValue(10)), 3),
                                                                 new ArthExp(new VarExp("b"), new VarExp("c"), 3),
                                                                 new ValExp(new IntValue(10)),
                                                                new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                                                                new CompStmt(new PrintStmt(new ValExp(new IntValue(100))), new PrintStmt(new ValExp(new IntValue(200)))),
                                                                new PrintStmt(new ValExp(new IntValue(300)))),
                                                                new PrintStmt(new ValExp(new IntValue(300))))))))));
        StackInterface<StatementInterface> stk17 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl17 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list17 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap17 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl17 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv17 = new MyMap<>();
        stk17.push(exp17);
        RunExampleCommand runEx17 = null;
        try {
            exp17.typeCheck(typeEnv17);
            ProgramState state17 = new ProgramState(stk17, symTbl17, list17, fileTbl17, heap17);
            RepositoryInterface repository17 = new Repository("exp17.txt");
            repository17.addProgram(state17);
            Controller controller17 = new Controller(repository17);
            runEx17 = new RunExampleCommand("17", exp17.toString(), controller17);
            examples.add(runEx17);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 17!");
            a.show();
        }
        //FOR 2
        StatementInterface exp18 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                new CompStmt(new NewStmt("a", new ValExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new ForStmt("v",new ValExp(new IntValue(0)),
                                new ValExp(new IntValue(3)),
                                new ArthExp(new VarExp("v"), new ValExp(new IntValue(1)), 1),
                                new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArthExp(new VarExp("v"), new HeapReadExp(new VarExp("a")), 3))))), new PrintStmt(new HeapReadExp(new VarExp("a")))))));
        StackInterface<StatementInterface> stk18 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl18 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list18 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap18 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl18 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv18 = new MyMap<>();
        stk18.push(exp18);
        RunExampleCommand runEx18 = null;
        try {
            exp18.typeCheck(typeEnv18);
            ProgramState state18 = new ProgramState(stk18, symTbl18, list18, fileTbl18, heap18);
            RepositoryInterface repository18 = new Repository("exp18.txt");
            repository18.addProgram(state18);
            Controller controller18 = new Controller(repository18);
            runEx18 = new RunExampleCommand("18", exp18.toString(), controller18);
            examples.add(runEx18);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 18!");
            a.show();
        }

        //DO WHILE do (v=v-1) while (v>0)
        StatementInterface exp19 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntValue(4))),
                        new CompStmt(new DoWhileStmt(
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArthExp(new VarExp("v"), new ValExp(new IntValue(1)), 2))),
                                new RelationalExp( new VarExp("v"), new ValExp(new IntValue(0)), 5)), new PrintStmt(new VarExp("v")))));

        StackInterface<StatementInterface> stk19 = new MyStack<StatementInterface>();
        MapInterface<String, ValueInterface> symTbl19 = new MyMap<String, ValueInterface>();
        ListInterface<ValueInterface> list19 = new MyList<ValueInterface>();
        HeapInterface<Integer, ValueInterface> heap19 = new MyHeap<>();
        FileTableInterface<String, BufferedReader> fileTbl19 = new FileTable<>();
        MapInterface<String, TypeInterface> typeEnv19 = new MyMap<>();
        stk19.push(exp19);
        RunExampleCommand runEx19 = null;
        try {
            exp19.typeCheck(typeEnv19);
            ProgramState state19 = new ProgramState(stk19, symTbl19, list19, fileTbl19, heap19);
            RepositoryInterface repository19 = new Repository("exp19.txt");
            repository19.addProgram(state19);
            Controller controller19 = new Controller(repository19);
            runEx19 = new RunExampleCommand("19", exp19.toString(), controller19);
            examples.add(runEx19);
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Type Check failed for EXP 19!");
            a.show();
        }
        return examples;
    }
}