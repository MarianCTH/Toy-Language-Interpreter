package Model.State;

import Exception.ToyLangException;
import Model.Value.IValue;
import Model.Value.Type.IType;

import java.util.Map;

public interface ISymTable {

    void declValue(String name, IType type) throws ToyLangException;
    IValue getValue(String name) throws ToyLangException;
    void setValue(String name, IValue value) throws ToyLangException;
    String toString();

    ISymTable copy() throws ToyLangException;
    Map<String, IValue> getMap();
}