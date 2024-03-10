package com.example.demogui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.*;
import program.ProgramState;
import statement.StatementInterface;
import value.StringValue;
import value.ValueInterface;
import view.textInterface.commands.RunExampleCommand;

import java.io.BufferedReader;
import java.util.List;

import model.MapEntry;
import model.HeapEntry;



public class MainController {
    @FXML
    private DisplayListController controller;
    private RunExampleCommand ex;
    private ProgramState prg;

    @FXML
    private TextField TextFiled1;

    @FXML
    private TableView<HeapEntry> HeapTableView;

    @FXML
    private TableColumn<HeapEntry,String>  AdressColumn;

    @FXML
    private TableColumn<HeapEntry,String>  ValueColumn;

    @FXML
    private ListView<StringValue> FileTableListView;

    @FXML
    private ListView<ValueInterface> OutListView;

    @FXML
    private ListView<ProgramState> PrgStatesIdListView;

    @FXML
    private ListView<StatementInterface> StackListView;

    @FXML
    private TableView<MapEntry> SymTableView;

    @FXML
    private TableColumn<MapEntry,String>  VNameColumn;

    @FXML
    private TableColumn<MapEntry,String>  VariableValueColumn;

    @FXML
    private Button RunOneStepButton;

    public void setController(DisplayListController controller){
        this.controller=controller;

        this.controller.getList_of_istmt().getSelectionModel().selectedItemProperty().addListener((a,b,ex)->this.showDataForSelectedExample(ex));
    }
    @FXML
    public void initialize(){
        this.TextFiled1.setEditable(false);

        this.AdressColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry,String>("heapAddress"));
        System.out.println(this.AdressColumn.toString());
        this.ValueColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry,String>("heapValue"));
        System.out.println(this.ValueColumn.toString());

        this.VNameColumn.setCellValueFactory(new PropertyValueFactory<MapEntry,String>("varName"));
        System.out.println(this.VNameColumn.toString());
        this.VariableValueColumn.setCellValueFactory(new PropertyValueFactory<MapEntry,String>("value"));
        System.out.println(this.VariableValueColumn.toString());

        this.FileTableListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<StringValue>() {
            @Override
            public String toString(StringValue stringValue) {
                return stringValue.toString();
            }

            @Override
            public StringValue fromString(String s) {
                return null;
            }
        }));
        this.OutListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<ValueInterface>() {
            @Override
            public String toString(ValueInterface stringValue) {
                return stringValue.toString();
            }

            @Override
            public ValueInterface fromString(String s) {
                return null;
            }
        }));

        this.StackListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<StatementInterface>() {
            @Override
            public String toString(StatementInterface stmt) {
                return stmt.toString();
            }

            @Override
            public StatementInterface fromString(String s) {
                return null;
            }
        }));
        System.out.println(this.StackListView.getItems().toArray());
        this.PrgStatesIdListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<ProgramState>() {
            @Override
            public String toString(ProgramState p) {
                return Integer.toString(p.getId());
            }

            @Override
            public ProgramState fromString(String s) {
                return null;
            }
        }));

        this.PrgStatesIdListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.PrgStatesIdListView.getSelectionModel().selectedItemProperty().addListener((a,b,state)->{
            if(state != null)
                showDataForSelectedProgram(state);
        });

        this.RunOneStepButton.setOnAction(actionEvent -> runOneStep(this.controller.getList_of_istmt().getSelectionModel().getSelectedItems().get(0)));
    }

    private void showDataForSelectedExample(RunExampleCommand runExample){
        this.SymTableView.getItems().clear();
        this.HeapTableView.getItems().clear();
        this.StackListView.getItems().clear();
        this.OutListView.getItems().clear();
        this.FileTableListView.getItems().clear();
        this.PrgStatesIdListView.getItems().clear();

        List<ProgramState> programStates = runExample.getController().getRepository().getProgramStatesList();

        if(programStates.size()!=0)
            this.prg=programStates.get(0);

        HeapInterface<Integer,ValueInterface> heap=this.prg.getHeap();
        FileTableInterface<String, BufferedReader> fileTable=prg.getFileTbl();
        ListInterface<ValueInterface> out=prg.getOut();
        MapInterface<String,ValueInterface> symt=prg.getMap();
        StackInterface<StatementInterface> stk=prg.getStack();

        heap.display().forEach((adress,value)->this.HeapTableView.getItems().add(new HeapEntry(adress.toString(),value.toString())));
        fileTable.display().forEach((filename,path)->this.FileTableListView.getItems().add(new StringValue(filename)));
        out.display().forEach((value)->this.OutListView.getItems().add(value));
        programStates.forEach((prgState)->this.PrgStatesIdListView.getItems().add(prgState));
        symt.display().forEach((name,value)->this.SymTableView.getItems().add(new MapEntry(name.toString(),value.toString())));
        stk.getStack().forEach((statment)->this.StackListView.getItems().add(statment));

        this.TextFiled1.setText(Integer.toString(prg.getId()));
    }

    private void showDataForSelectedProgram(ProgramState prgState){
        this.StackListView.getItems().clear();
        this.SymTableView.getItems().clear();

        MapInterface<String,ValueInterface> symt=prgState.getMap();
        StackInterface<StatementInterface> stk=prgState.getStack();

        symt.display().forEach((name,value)->this.SymTableView.getItems().add(new MapEntry(name.toString(),value.toString())));
        stk.getStack().forEach((statement)->this.StackListView.getItems().add(statement));

    }

    private void runOneStep(RunExampleCommand ex){

        try {
            ex.getController().oneStepFX();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        showDataForSelectedExample(ex);
        if (prg.getStack().isEmpty()) {
            //this.controller.getList_of_istmt().getItems().add(ex);
            showAlert("Stack is Empty", "The execution stack is now empty.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
