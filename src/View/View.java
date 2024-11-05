package View;

import Controller.IController;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import Exception.ToyLangException;
import java.io.IOException;

import Model.Statement.IStatement;
import Model.Statement.AssignStmt;
import Model.Statement.CompStmt;
import Model.Statement.VarDeclStmt;
import Model.Statement.PrintStmt;
import Model.Statement.IfStmt;

import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.Expression.ArithExp;

import Model.Value.Type.IntType;
import Model.Value.IntValue;
import Model.Value.BoolValue;
import Model.Value.Type.BoolType;

public class View implements IView {
    IController controller;

    public View(IController controller) {
        this.controller = controller;
    }

    public void displayMenu(){
        System.out.println("1. int v; v=2;Print(v) ");
        System.out.println("2. int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("3. bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v) ");
        System.out.println("0. Exit");
    }

    void RunFirstProgram() throws ToyLangException {
        IStatement ex1 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        this.controller.setProgram(ex1);
    }

    void RunSecondProgram() throws ToyLangException {
        IStatement ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp("+",new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        this.controller.setProgram(ex2);
    }

    void RunThirdProgram() throws ToyLangException {
        IStatement ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        this.controller.setProgram(ex3);
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while(true) {
            this.displayMenu();
            try{
                System.out.println("Enter the option: ");
                String option = reader.readLine().strip();

                switch(option){
                    case "1" -> {
                        this.RunFirstProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (ToyLangException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "2" -> {
                        this.RunSecondProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (ToyLangException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "3" -> {
                        this.RunThirdProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (ToyLangException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "0"->{
                        System.out.println("Exiting...");
                        return;
                    }
                    default->
                            System.out.println("Invalid option");
                }
            }
            catch (ToyLangException exception) {
                System.out.println(exception.getMessage());
                break;
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
