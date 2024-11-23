package Model.State;

import Exception.AddressOutOfBoundsException;
import Model.Value.IValue;
import java.util.Map;


public interface IHeapTable {
    int allocate(IValue value);
    IValue read(int address) throws AddressOutOfBoundsException;
    void write(int address, IValue value) throws AddressOutOfBoundsException;
    void deallocate(int address) throws AddressOutOfBoundsException;
    Map<Integer, IValue> getMap();
    void setContent(Map<Integer, IValue> map);
    Map<Integer, IValue> getContent();
}
