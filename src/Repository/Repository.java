package Repository;

import Model.State.PrgState;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    List<PrgState> programs;

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

}