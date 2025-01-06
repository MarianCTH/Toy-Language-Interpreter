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

public class Main {
    public static void main(String[] args) throws ToyLangException{
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        IStatement ex1 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        IRepository repo1 = new Repository("log1.txt");
        Controller ctrl1 = new Controller(repo1,true);
        try{
            ctrl1.setProgram(ex1);
            menu.addCommand(new RunExample("1",ex1.toString(),ctrl1));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp("+",new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        IRepository repo2 = new Repository("log2.txt");
        Controller ctrl2 = new Controller(repo2,true);
        try{
            ctrl2.setProgram(ex2);
            menu.addCommand(new RunExample("2",ex2.toString(),ctrl2));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        IRepository repo3 = new Repository("log3.txt");
        Controller ctrl3 = new Controller(repo3,true);
        try{
            ctrl3.setProgram(ex3);
            menu.addCommand(new RunExample("3",ex3.toString(),ctrl3));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

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
        try{
            ctrl4.setProgram(ex4);
            menu.addCommand(new RunExample("4",ex4.toString(),ctrl4));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex5 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())), // Ref int v;
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))), // new(v,20);
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))), // Ref Ref int a;
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")), // new(a,v);
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")), // print(v);
                                                new PrintStmt(new VarExp("a"))  // print(a);
                                        )
                                )
                        )
                )
        );

        IRepository repo5 = new Repository("log5.txt");
        Controller ctrl5 = new Controller(repo5,true);
        try{
            ctrl5.setProgram(ex5);
            menu.addCommand(new RunExample("5",ex5.toString(),ctrl5));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex6 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())), // Ref int v;
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))), // new(v,20);
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))), // Ref Ref int a;
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")), // new(a,v);
                                        new CompStmt(
                                                new PrintStmt(new ReadHeapExp(new VarExp("v"))), // print(rH(v));
                                                new PrintStmt(
                                                        new ArithExp(
                                                                '+',
                                                                new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), // rH(rH(a))
                                                                new ValueExp(new IntValue(5)) // +5
                                                        )
                                                ) // print(rH(rH(a)) + 5)
                                        )
                                )
                        )
                )
        );
        IRepository repo6 = new Repository("log6.txt");
        Controller ctrl6 = new Controller(repo6,true);
        try{
            ctrl6.setProgram(ex6);
            menu.addCommand(new RunExample("6",ex6.toString(),ctrl6));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex7 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())), // Ref int v;
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))), // new(v,20);
                        new CompStmt(
                                new PrintStmt(new ReadHeapExp(new VarExp("v"))), // print(rH(v));
                                new CompStmt(
                                        new WriteHeap(new VarExp("v"), new ValueExp(new IntValue(30))), // wH(v,30);
                                        new PrintStmt(
                                                new ArithExp(
                                                        '+',
                                                        new ReadHeapExp(new VarExp("v")), // rH(v)
                                                        new ValueExp(new IntValue(5)) // +5
                                                )
                                        ) // print(rH(v) + 5);
                                )
                        )
                )
        );
        IRepository repo7 = new Repository("log7.txt");
        Controller ctrl7 = new Controller(repo7,true);
        try{
            ctrl7.setProgram(ex7);
            menu.addCommand(new RunExample("7",ex7.toString(),ctrl7));
        } catch (ToyLangException e) {
            System.out.println(e);
        }


        IStatement ex8 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())), // Ref int v;
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))), // new(v,20);
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))), // Ref Ref int a;
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")), // new(a,v);
                                        new CompStmt(
                                                new NewStmt("v", new ValueExp(new IntValue(30))), // new(v,30);
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))) // print(rH(rH(a)))
                                        )
                                )
                        )
                )
        );
        IRepository repo8 = new Repository("log8.txt");
        Controller ctrl8 = new Controller(repo8,true);
        try{
            ctrl8.setProgram(ex8);
            menu.addCommand(new RunExample("8",ex8.toString(),ctrl8));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex9 = new CompStmt(
                new VarDeclStmt("v", new IntType()), // int v;
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(4))), // v = 4;
                        new CompStmt(
                                new WhileStmt( // while (v > 0)
                                        new ArithExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")), // print(v);
                                                new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ValueExp(new IntValue(1)))) // v = v - 1
                                        )
                                ),
                                new PrintStmt(new VarExp("v")) // print(v)
                        )
                )
        );
        IRepository repo9 = new Repository("log9.txt");
        Controller ctrl9 = new Controller(repo9,true);
        try{
            ctrl9.setProgram(ex9);
            menu.addCommand(new RunExample("9",ex9.toString(),ctrl9));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex10 = new CompStmt(
                new VarDeclStmt("v", new IntType()), // int v;
                new CompStmt(
                        new VarDeclStmt("a", new RefType(new IntType())), // Ref int a;
                        new CompStmt(
                                new AssignStmt("v", new ValueExp(new IntValue(10))), // v = 10;
                                new CompStmt(
                                        new NewStmt("a", new ValueExp(new IntValue(22))), // new(a, 22);
                                        new CompStmt(
                                                new ForkStmt( // fork statement
                                                        new CompStmt(
                                                                new WriteHeap(new VarExp("a"), new ValueExp(new IntValue(30))), // wH(a, 30);
                                                                new CompStmt(
                                                                        new AssignStmt("v", new ValueExp(new IntValue(32))), // v = 32;
                                                                        new CompStmt(
                                                                                new PrintStmt(new VarExp("v")), // print(v);
                                                                                new PrintStmt(new ReadHeapExp(new VarExp("a"))) // print(rH(a));
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")), // print(v);
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a"))) // print(rH(a));
                                                )
                                        )
                                )
                        )
                )
        );

        IRepository repo10 = new Repository("log10.txt");
        Controller ctrl10 = new Controller(repo10, true);
        try{
            ctrl10.setProgram(ex10);
            menu.addCommand(new RunExample("10",ex10.toString(),ctrl10));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex11 = new CompStmt(
                new VarDeclStmt("a", new RefType(new IntType())), // Ref(int) a;
                new CompStmt(
                        new VarDeclStmt("v", new IntType()), // int v;
                        new CompStmt(
                                new NewStmt("a", new ValueExp(new IntValue(10))), // new(a, 10);
                                new CompStmt(
                                        new ForkStmt( // fork(
                                                new CompStmt(
                                                        new AssignStmt("v", new ValueExp(new IntValue(20))), // v = 20;
                                                        new CompStmt(
                                                                new ForkStmt( // fork(
                                                                        new CompStmt(
                                                                                new WriteHeap(new VarExp("a"), new ValueExp(new IntValue(40))), // wH(a, 40);
                                                                                new PrintStmt(new ReadHeapExp(new VarExp("a"))) // print(rH(a));
                                                                        )
                                                                ),
                                                                new PrintStmt(new VarExp("v")) // print(v);
                                                        )
                                                )
                                        ),
                                        new CompStmt(
                                                new AssignStmt("v", new ValueExp(new IntValue(30))), // v = 30;
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")), // print(v);
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a"))) // print(rH(a));
                                                )
                                        )
                                )
                        )
                )
        );


        IRepository repo11 = new Repository("log11.txt");
        Controller ctrl11 = new Controller(repo11, true);
        try{
            ctrl11.setProgram(ex11);
            menu.addCommand(new RunExample("11",ex11.toString(),ctrl11));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex12 = new CompStmt(
                new VarDeclStmt("varf", new StringType()), // string varf;
                new CompStmt(
                        new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), // varf = "test.in";
                        new CompStmt(
                                new OpenRFile(new VarExp("varf")), // open file varf;
                                new CompStmt(
                                        new ForkStmt( // fork(
                                                new CompStmt(
                                                        new VarDeclStmt("varc", new IntType()), // int varc;
                                                        new CompStmt(
                                                                new ReadFile(new VarExp("varf"), "varc"), // read file(varf, varc);
                                                                new PrintStmt(new VarExp("varc")) // print(varc);
                                                        )
                                                )
                                        ),
                                        new CompStmt(
                                                new VarDeclStmt("varc", new IntType()), // int varc;
                                                new CompStmt(
                                                        new ReadFile(new VarExp("varf"), "varc"), // read from file(varf, varc);
                                                        new CompStmt(
                                                                new PrintStmt(new VarExp("varc")), // print(varc);
                                                                new CloseRFile(new VarExp("varf")) // close file(varf);
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        IRepository repo12 = new Repository("log12.txt");
        Controller ctrl12 = new Controller(repo12, true);
        try{
            ctrl12.setProgram(ex12);
            menu.addCommand(new RunExample("12",ex12.toString(),ctrl12));
        } catch (ToyLangException e) {
            System.out.println(e);
        }

        IStatement ex13 = new CompStmt(
                new VarDeclStmt("v", new IntType()), // int v;
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new StringValue("hello"))), // v = "hello"; (Type Error: trying to assign a string to an int)
                        new CompStmt(
                                new WhileStmt( // while (v > 0)
                                        new ArithExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")), // print(v);
                                                new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ValueExp(new IntValue(1)))) // v = v - 1
                                        )
                                ),
                                new PrintStmt(new VarExp("v")) // print(v)
                        )
                )
        );
        IRepository repo13 = new Repository("log13.txt");
        Controller ctrl13 = new Controller(repo13, true);
        try{
            ctrl13.setProgram(ex13);
            menu.addCommand(new RunExample("13",ex13.toString(),ctrl13));
        } catch (ToyLangException e) {
            System.out.println("[Statement 13] " + e);
        }
a
        IStatement ex14 = new CompStmt(
                new VarDeclStmt("v", new IntType()), // int v;
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(5))), // v = 5;
                        new CompStmt(
                                new PrintStmt(new ArithExp("+", new VarExp("v"), new ValueExp(new StringValue("hello")))), // v + "hello" (Type Error)
                                new PrintStmt(new VarExp("v")) // print(v)
                        )
                )
        );
        IRepository repo14 = new Repository("log14.txt");
        Controller ctrl14 = new Controller(repo14, true);
        try{
            ctrl14.setProgram(ex14);
            menu.addCommand(new RunExample("14",ex14.toString(),ctrl14));
        } catch (ToyLangException e) {
            System.out.println("[Statement 14] " + e);
            System.out.println("[Statement 14] Original code: " + ex14.toString());
        }

        IStatement ex15 = new CompStmt(
                new VarDeclStmt("v", new IntType()), // int v;
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(1))), // v = 1;
                        new IfStmt(
                                new VarExp("v"), // if (v) (Type Error: v is not a boolean)
                                new PrintStmt(new ValueExp(new StringValue("True"))),
                                new PrintStmt(new ValueExp(new StringValue("False")))
                        )
                )
        );
        extracted(ex15, menu);

        menu.show();
    }

    private static void extracted(IStatement ex15, TextMenu menu) {
        IRepository repo15 = new Repository("log15.txt");
        Controller ctrl15 = new Controller(repo15, true);
        try{
            ctrl15.setProgram(ex15);
            menu.addCommand(new RunExample("15", ex15.toString(),ctrl15));
        } catch (ToyLangException e) {
            System.out.println("[Statement 15] " + e);
        }
    }
}