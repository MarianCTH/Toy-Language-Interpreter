import Controller.Controller;
import Controller.IController;
import Exception.ToyLangException;

import Model.Expression.ArithExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.Statement.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Type.BoolType;
import Model.Value.Type.IntType;
import Model.Value.Type.StringType;
import Model.Value.StringValue;
import Repository.IRepository;
import Repository.Repository;
import View.TextMenu.ExitCommand;
import View.TextMenu.TextMenu;
import View.TextMenu.RunExample;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ToyLangException {
        IStatement ex1 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        IRepository repo1 = new Repository("log1.txt");
        Controller ctrl1 = new Controller(repo1,true);
        ctrl1.setProgram(ex1);

        IStatement ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp("+",new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        IRepository repo2 = new Repository("log2.txt");
        Controller ctrl2 = new Controller(repo2,true);
        ctrl2.setProgram(ex2);

        IStatement ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        IRepository repo3 = new Repository("log3.txt");
        Controller ctrl3 = new Controller(repo3,true);
        ctrl3.setProgram(ex3);

        IStatement ex4 = new CompStmt(
                new VarDeclStmt("varf", new StringType()),
                new CompStmt(
                        new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(
                                new OpenRFile(new VarExp("varf")),
                                new CompStmt(
                                        new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(
                                                new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(
                                                                new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(
                                                                        new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        IRepository repo4 = new Repository("log4.txt");
        Controller ctrl4 = new Controller(repo4,true);
        ctrl4.setProgram(ex4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctrl1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctrl2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctrl3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctrl4));
        menu.show();
    }
}