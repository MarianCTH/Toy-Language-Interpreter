package Controller;

import Model.Value.RefValue;
import Model.Value.IValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class GarbageCollector {
    public static Map<Integer, IValue> conservativeGarbageCollector(List<Integer> referencedAddresses, Map<Integer, IValue> heap) {
        List<Integer> allReachableAddresses = getAllReachableAddresses(referencedAddresses, heap);
        //all addresses that are reachable, not just directly from the symbol table but also from nested references within the heap

        return heap.entrySet().stream()
                .filter(entry -> allReachableAddresses.contains(entry.getKey()))
                //keep entries where the address is in the list of reachable addresses
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }

    private static List<Integer> getAllReachableAddresses(List<Integer> initialAddresses, Map<Integer, IValue> heap) {
        List<Integer> reachableAddresses = new ArrayList<>(initialAddresses);
        List<Integer> toVisit = new ArrayList<>(initialAddresses);

        for (int i = 0; i < toVisit.size(); i++) {
            Integer currentAddress = toVisit.get(i);
            IValue value = heap.get(currentAddress);

            if (value instanceof RefValue) {
                Integer nestedAddress = ((RefValue) value).getAddress();
                if (!reachableAddresses.contains(nestedAddress)) {
                    reachableAddresses.add(nestedAddress);
                    toVisit.add(nestedAddress);
                }
            }
        }

        return reachableAddresses;
    }

}
