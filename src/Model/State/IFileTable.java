package Model.State;

import Exception.ToyLangException;

public interface IFileTable {
    void openFile(String name) throws ToyLangException;
    void closeFile(String name) throws ToyLangException;
    int readFile(String name) throws ToyLangException;
    String toString();
}
