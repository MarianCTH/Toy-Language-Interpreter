package Repository;

import Model.State.PrgState;
import java.util.List;
import Exception.ToyLangException;

public interface IRepository {
    List <PrgState> getPrgList();
    void setPrgList(List <PrgState> list);
    void add(PrgState e);
    void clear();
    void logPrgStateExec(PrgState program);
}
