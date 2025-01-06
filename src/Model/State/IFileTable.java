package Model.State;

import Exception.ToyLangException;

import java.util.List;

public interface IFileTable {
    void openFile(String name) throws ToyLangException;
    void closeFile(String name) throws ToyLangException;
    int readFile(String name) throws ToyLangException;
    String toString();

    List<String> getKeys();
}
