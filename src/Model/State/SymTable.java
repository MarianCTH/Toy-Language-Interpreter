package Model.State;

import ADT.Dictionary.GenericDictionary;
import ADT.Dictionary.IGenericDictionary;
import Exception.KeyNotFoundException;
import Exception.ToyLangException;
import Exception.SymbolAlreadyExistsAppException;
import Exception.SymbolNotFoundException;
import Model.Value.IValue;
import Model.Value.Type.IType;

import java.util.Map;

public class SymTable implements ISymTable {
    IGenericDictionary<String, IValue> data;

    public SymTable() {
        data = new GenericDictionary<>();
    }

    @Override
    public void declValue(String name, IType type) throws SymbolAlreadyExistsAppException {
        if (data.exists(name))
            throw new SymbolAlreadyExistsAppException("Symbol " + name + " already exists.");

        data.insert(name, type.getDefaultValue());
    }

    @Override
    public IValue getValue(String name) throws SymbolNotFoundException {
        try {
            return data.lookup(name);
        } catch (KeyNotFoundException exception) {
            throw new SymbolNotFoundException("Symbol " + name + " not found.");
        }
    }

    @Override
    public void setValue(String name, IValue value) throws SymbolNotFoundException, KeyNotFoundException {
        if (!data.exists(name))
            throw new SymbolNotFoundException("Symbol " + name + " not found.");

        if (!data.lookup(name).getType().equals(value.getType()))
            throw new SymbolNotFoundException("Symbol " + name + " does not have the same type as new value.");

        data.insert(name, value);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder("SymTable:\n");
        try {
            for (String key : data.getKeys()) {
                answer.append(key).append("(").append(data.lookup(key).getType().toString()).append(")").append(":-> ").append(data.lookup(key).toString()).append("\n");
            }
        } catch (ToyLangException exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return answer.toString();
    }

    @Override
    public ISymTable copy() throws ToyLangException {
        ISymTable newSymTable = new SymTable();

        for(String key:data.getKeys()){
            newSymTable.declValue(key, data.lookup(key).getType());
            newSymTable.setValue(key, data.lookup(key).clone());
        }

        return newSymTable;
    }

    public Map<String, IValue> getMap() {
        return this.data.getMap();
    }
}