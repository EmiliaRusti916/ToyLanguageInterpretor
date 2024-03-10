package repository;
import exceptions.MyException;
import program.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface{
    private List<ProgramState> programStates;
    private String logFilePath;
    public int currentIndex;

    public Repository(String file) {

        this.programStates = new ArrayList<>();
        this.logFilePath = file;
        this.currentIndex = -1;
    }

    public List<ProgramState> getProgramStatesList() {
        return this.programStates;
    }

    @Override
    public void setProgramStatesList(List<ProgramState> programStates) {
        this.programStates.clear();
        this.programStates.addAll(programStates);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

// @Override
   // public ProgramState getCurrentProgram() {
   //     return programStates.get(0);
    //}

    @Override
    public void addProgram(ProgramState state) {
        this.currentIndex = this.currentIndex + 1;
        this.programStates.add(state);
    }

    @Override
    public void logPrgStateExec(ProgramState programState) throws MyException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)))) {
            logFile.println(programState.toString());

        } catch (IOException e) {
            throw new MyException(e.getMessage());
        }
    }


}
