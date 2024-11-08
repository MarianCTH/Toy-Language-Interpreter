package Model.State;

import ADT.List.GenericList;
import ADT.List.IGenericList;

import java.util.List;

public class Output implements IOutput {
    IGenericList<String> data;

    public Output() {
        this.data = new GenericList<>();
    }

    @Override
    public List<String> getOutputAsList() {
        return this.data.getAll();
    }

    @Override
    public String getOutput() {
        StringBuilder answer = new StringBuilder();
        for (String elem : this.data.getAll()) {
            answer.append(elem);
            answer.append("\n");
        }
        return answer.toString();
    }

    @Override
    public void appendToOutput(String string) {
        this.data.add(string);
    }

    @Override
    public void setOutput(String string) {
        this.data.clear();
        this.data.add(string);
    }

    @Override
    public String toString() {
        return "Output:\n" + this.getOutput();
    }
}