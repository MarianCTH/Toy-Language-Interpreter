package View.TextMenu;

import Model.Expression.*;
import Model.Statement.*;
import Model.Value.*;
import Model.Value.Type.*;

public class Statements {

    public static IStatement example1() {
        return new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))
                )
        );
    }

    public static IStatement example2() {
        return new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp("+",new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
    }

    public static IStatement example3() {
        return new CompStmt(
                new VarDeclStmt("a", new BoolType()),
                new CompStmt(
                        new VarDeclStmt("v", new IntType()),
                        new CompStmt(
                                new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExp("a"),
                                                new AssignStmt("v", new ValueExp(new IntValue(2))),
                                                new AssignStmt("v", new ValueExp(new IntValue(3))
                                                )),
                                        new PrintStmt(new VarExp("v"))
                                )
                        )
                )
        );
    }

    public static IStatement example4() {
        return new CompStmt(
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
    }

    public static IStatement example5() {
        return new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a"))
                                        )
                                )
                        )
                )
        );
    }

    public static IStatement example6() {
        return new CompStmt(
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
    }
    public static IStatement example7() {
        return new CompStmt(
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
    }

    public static IStatement example8() {
        return new CompStmt(
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
    }

    public static IStatement example9() {
        return new CompStmt(
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
    }
    public static IStatement example10() {
        return new CompStmt(
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
    }

    public static IStatement example11() {

        return new CompStmt(
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
    }
    public static IStatement example12() {

        return new CompStmt(
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
    }
    public static IStatement example13() {
        return new CompStmt(
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
    }
    public static IStatement example14() {
        return new CompStmt(
                new VarDeclStmt("v", new IntType()), // int v;
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(5))), // v = 5;
                        new CompStmt(
                                new PrintStmt(new ArithExp("+", new VarExp("v"), new ValueExp(new StringValue("hello")))), // v + "hello" (Type Error)
                                new PrintStmt(new VarExp("v")) // print(v)
                        )
                )
        );
    }
    public static IStatement example15() {
        return new CompStmt(
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
    }
}
