package Model.State;

import Model.Value.IValue;
import java.util.Map;
import ADT.Dictionary.IGenericDictionary;
import ADT.Dictionary.GenericDictionary;
import Exception.AddressOutOfBoundsException;
import Exception.KeyNotFoundException;

public class HeapTable implements IHeapTable{
    IGenericDictionary<Integer, IValue> heap;
    int firstFree;

    public HeapTable() {
        this.heap = new GenericDictionary<>();
        this.firstFree = 1;
    }

    @Override
    public int allocate(IValue value) {
        int allocatedAddress = firstFree;

        heap.insert(firstFree, value);
        firstFree += 1;

        return allocatedAddress;
    }

    @Override
    public IValue read(int address) throws AddressOutOfBoundsException {
        try {
            return heap.lookup(address);
        } catch (KeyNotFoundException e) {
            throw new AddressOutOfBoundsException("Address " + Integer.toString(address) + " out of bounds.");
        }
    }

    @Override
    public void write(int address, IValue value) throws AddressOutOfBoundsException {
        if (!heap.getKeys().contains(address)) {
            throw new AddressOutOfBoundsException("Address " + Integer.toString(address) + " out of bounds.");
        }
        heap.insert(address, value);
    }

    @Override
    public void deallocate(int address) throws AddressOutOfBoundsException {
        try {
            heap.delete(address);
        } catch (KeyNotFoundException e) {
            throw new AddressOutOfBoundsException("Address " + Integer.toString(address) + " out of bounds.");
        }
    }

    public Map<Integer, IValue> getMap() {
        return this.heap.getMap();
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder("Heap:\n");
        for (Map.Entry<Integer, IValue> entry : heap.getMap().entrySet()) {
            answer.append(entry.getKey().toString()).append(" -> ").append(entry.getValue().toString()).append("\n");
        }
        return answer.toString();
    }

    @Override
    public void setContent(Map<Integer, IValue> newContent) {
        this.heap.setMap(newContent);
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return this.heap.getMap();
    }
}
