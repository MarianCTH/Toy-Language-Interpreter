import Controller.Controller;
import Repository.IRepository;
import Repository.Repository;

import Model.Expression.*;
import Model.Statement.*;
import Model.Value.*;
import Model.Value.Type.*;

import View.TextMenu.ExitCommand;
import View.TextMenu.TextMenu;
import View.TextMenu.RunExample;
import Exception.ToyLangException;
import View.TextMenu.Statements;

public class Main {
    public static void main(String[] args) throws ToyLangException{
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        addExampleToMenu(menu, "1", Statements.example1(), "log1.txt");
        addExampleToMenu(menu, "2", Statements.example2(), "log2.txt");
        addExampleToMenu(menu, "3", Statements.example3(), "log3.txt");
        addExampleToMenu(menu, "4", Statements.example4(), "log4.txt");
        addExampleToMenu(menu, "5", Statements.example5(), "log5.txt");
        addExampleToMenu(menu, "6", Statements.example6(), "log6.txt");
        addExampleToMenu(menu, "7", Statements.example7(), "log7.txt");
        addExampleToMenu(menu, "8", Statements.example8(), "log8.txt");
        addExampleToMenu(menu, "9", Statements.example9(), "log9.txt");
        addExampleToMenu(menu, "10", Statements.example10(), "log10.txt");
        addExampleToMenu(menu, "11", Statements.example11(), "log11.txt");
        addExampleToMenu(menu, "12", Statements.example12(), "log12.txt");
        addExampleToMenu(menu, "13", Statements.example13(), "log13.txt");
        addExampleToMenu(menu, "14", Statements.example14(), "log14.txt");
        addExampleToMenu(menu, "15", Statements.example15(), "log15.txt");

        menu.show();

        menu.show();
    }
    private static void addExampleToMenu(TextMenu menu, String id, IStatement example, String logFile) {
        IRepository repo = new Repository(logFile);
        Controller ctrl = new Controller(repo, true);
        try {
            ctrl.setProgram(example);
            menu.addCommand(new RunExample(id, example.toString(), ctrl));
        } catch (ToyLangException e) {
            System.out.println("Error adding example " + id + ": " + e.getMessage());
        }
    }
}