package controller;

import exceptions.MyException;
import model.ListInterface;
import model.StackInterface;
import program.ProgramState;
import repository.RepositoryInterface;
import statement.StatementInterface;
import value.RefValue;
import value.ValueInterface;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private final RepositoryInterface repository;
    boolean displayFlag;
    ExecutorService executor;

    public Controller(RepositoryInterface repository) {
        this.repository = repository;
        this.displayFlag = true;
    }

    public RepositoryInterface getRepository() {
        return repository;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void oneStepForAllPrg(List<ProgramState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep))
                .collect(Collectors.toList());
        List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
                if(displayFlag)
                    System.out.println(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        repository.setProgramStatesList(prgList);
    }

    public void oneStepFX() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList = removeCompletedPrograms(repository.getProgramStatesList());

        /*prgList.get(repository.getCurrentIndex()).getHeap().setHeap(unsafeGarbageCollector(
                getAddrFromSymTable(prgList.get(repository.getCurrentIndex()).getMap().display().values()),
                prgList.get(repository.getCurrentIndex()).getHeap().display()));
        oneStepForAllPrg(prgList);
        prgList = removeCompletedPrograms(repository.getProgramStatesList());*/
        this.oneStepForAllPrg(prgList);
        List<Integer> addresses = new ArrayList<>();
        List<Integer> addr = prgList.stream().map(p -> getAddrFromSymTable(p.getMap().display().values())).flatMap(Collection::stream).toList();
        addresses.addAll(addr);
        //System.out.println(addresses);
        prgList.get(0).getHeap().setHeap(unsafeGarbageCollector(addresses, prgList.get(0).getHeap().display()));
        prgList = removeCompletedPrograms(repository.getProgramStatesList());
        executor.shutdownNow();
        repository.setProgramStatesList(prgList);
    }
    public void allSteps() throws MyException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList = removeCompletedPrograms(repository.getProgramStatesList());
        while (!prgList.isEmpty()) {
            this.oneStepForAllPrg(prgList);
            List<Integer> addresses = new ArrayList<>();
            List<Integer> addr = prgList.stream().map(p -> getAddrFromSymTable(p.getMap().display().values())).flatMap(Collection::stream).toList();
            addresses.addAll(addr);
            //System.out.println(addresses);
            prgList.get(0).getHeap().setHeap(unsafeGarbageCollector(addresses, prgList.get(0).getHeap().display()));
            prgList = removeCompletedPrograms(repository.getProgramStatesList());
        }
        executor.shutdownNow();
        repository.setProgramStatesList(prgList);
    }
    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates) {
        return programStates.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }
    public Map<Integer, ValueInterface> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,ValueInterface> heap){
        List<Integer> heapAddr=getAddrFromHeap(heap.values());
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey())||heapAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}
    public List<Integer> getAddrFromSymTable(Collection<ValueInterface> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }
    public List<Integer> getAddrFromHeap(Collection<ValueInterface> heapValues){
        return heapValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }
    /*
    public void displayCurrentProgram(){
        ProgramState prgState = this.repository.getCurrentProgram();
        System.out.println(prgState.toString());
    }
     */
    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }
}
