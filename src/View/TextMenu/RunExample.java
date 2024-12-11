package View.TextMenu;

import Controller.Controller;
import Exception.ToyLangException;

import java.io.IOException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allStep();
        }
        catch (InterruptedException | ToyLangException e){
            System.out.println(e.getMessage());
        }
    }
}
