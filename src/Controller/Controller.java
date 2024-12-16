package Controller;

import Exception.ToyLangException;
import Model.State.*;
import Model.Statement.IStatement;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Controller.GarbageCollector.getAddrFromSymTable;
import static Controller.GarbageCollector.conservativeGarbageCollector;


public class Controller implements IController {
    IRepository repository;
    boolean displayFlag;
    ExecutorService executor;

    public boolean getDisplayFlag() {
        return displayFlag;
    }

    @Override
    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public Controller(IRepository repository, boolean displayFlag) {
        this.repository = repository;
        this.displayFlag = displayFlag;
    }

    @Override
    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg ->repository.logPrgStateExec(prg));
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        prgList.addAll(newPrgList);
        prgList.forEach(prg -> repository.logPrgStateExec(prg));
        if (this.displayFlag) {
            this.displayCurrentState();
        }
        repository.setPrgList(prgList);
    }

    @Override
    public void allStep() throws ToyLangException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        List<PrgState>  prgList=removeCompletedPrg(repository.getPrgList());
        while(!prgList.isEmpty()){
            prgList.forEach(prg -> prg.getHeapTable().setContent(conservativeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getContent().values()),
                    prg.getHeapTable().getContent())));


            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();

        repository.setPrgList(prgList);
    }

    @Override
    public void displayCurrentState() {
        this.repository.getPrgList().forEach(program -> System.out.println(program.toString() + "\n"));
    }

    @Override
    public void setProgram(IStatement statement) {
        this.repository.clear();
        this.repository.add(new PrgState(new ExecutionStack(), new SymTable(), new Output(), statement, new FileTable(), new HeapTable()));
        if (this.displayFlag) {
            this.displayCurrentState();
        }
    }

    @Override
    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }
}