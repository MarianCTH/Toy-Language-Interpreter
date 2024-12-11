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
    public List<PrgState> getPrgList() {
        return programs;
    }

    public void setPrgList(List <PrgState> l){
        this.programs = l;
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
    public void logPrgStateExec(PrgState program){
        PrintWriter logFile;
        try{
            logFile= new PrintWriter(new BufferedWriter(new FileWriter(this.LogFilePath, true)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logFile.println(program.toString());
        logFile.close();
    }
}