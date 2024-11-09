package Model.State;

import ADT.Dictionary.IGenericDictionary;
import ADT.Dictionary.GenericDictionary;
import Exception.ToyLangException;
import Exception.FileAlreadyOpenException;
import Exception.FileNotOpenException;
import Exception.KeyNotFoundException;
import Exception.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;


public class FileTable implements IFileTable{
    IGenericDictionary<String, BufferedReader> files;

    public FileTable() {
        this.files = new GenericDictionary<>();
    }

    @Override
    public void openFile(String name) throws ToyLangException {
        if (files.exists(name))
            throw new FileAlreadyOpenException("File " + name + " already open for reading");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            this.files.insert(name, reader);
        } catch (FileNotFoundException e) {
            throw new ToyLangException("Error opening file " + name);
        }
    }

    @Override
    public void closeFile(String name) throws ToyLangException {
        try {
            files.delete(name);
        } catch (KeyNotFoundException exception) {
            throw new FileNotOpenException("File " + name + " cannot be closed");
        }
    }

    @Override
    public int readFile(String name) throws ToyLangException {
        BufferedReader reader;

        try {
            reader = files.lookup(name);
        } catch (KeyNotFoundException exception) {
            throw new FileNotOpenException("File " + name + " cannot be read from");
        }

        String data;

        try {
            data = reader.readLine();
        } catch (IOException e) {
            throw new InvalidFileFormatException("Invalid line in file");
        }

        if (data == null) {
            data = "0";
        }

        int answer;

        try {
            answer = Integer.parseInt(data);
        } catch (NumberFormatException exception) {
            throw new InvalidFileFormatException("Invalid line in file");
        }

        return answer;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();

        answer.append("FileTable:\n");
        for (String name : files.getKeys()) {
            answer.append(name).append("\n");
        }

        return answer.toString();
    }
}
