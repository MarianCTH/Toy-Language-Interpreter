import Controller.Controller;
import Controller.IController;
import Exception.ToyLangException;

import Repository.IRepository;
import Repository.Repository;
import View.IView;
import View.View;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ToyLangException {
        IRepository repository = new Repository();
        IController controller = new Controller(repository,true);
        IView view = new View(controller);

        view.run();
    }
}