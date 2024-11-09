package Repository;

import Model.State.PrgState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class Repository implements IRepository {
    List<PrgState> programs;
    String LogFilePath;

    public Repository(String logFilePath) {
        programs = new ArrayList<>();
        this.LogFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getProgramsList() {
        return programs;
    }

    @Override
    public void add(PrgState program) {
        programs.add(program);
    }

    @Override
    public void clear() {
        programs = new ArrayList<>();
    }

    @Override
    public PrgState getCrtPrg() {
        return programs.get(programs.size() - 1);
    }

    @Override
    public void logPrgStateExec() {
        PrintWriter logFile;
        try{
            logFile= new PrintWriter(new BufferedWriter(new FileWriter(this.LogFilePath, true)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logFile.println(getCrtPrg().toString());
        logFile.close();
    }
}