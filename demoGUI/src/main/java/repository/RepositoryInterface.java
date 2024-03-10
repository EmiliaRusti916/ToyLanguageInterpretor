package repository;

import exceptions.MyException;
import program.ProgramState;

import java.io.IOException;
import java.util.List;

public interface RepositoryInterface {
   // public ProgramState getCurrentProgram();
    public void addProgram(ProgramState state);
    public void logPrgStateExec(ProgramState programState) throws MyException, IOException;
    List<ProgramState> getProgramStatesList();
    void setProgramStatesList(List<ProgramState> programStates);
    int getCurrentIndex();
}
