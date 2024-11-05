package Repository;

import Model.State.PrgState;
import java.util.List;
import Exception.ToyLangException;

public interface IRepository {
    List <PrgState> getProgramsList();
    void add(PrgState e);
    void clear();
    PrgState getCrtPrg() throws ToyLangException;
}
