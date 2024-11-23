package Controller;

import Model.Value.RefValue;
import Model.Value.IValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;

public class GarbageCollector {
    public static Map<Integer, IValue> unsafeGarbageCollector(List<Integer> referencedAddresses, Map<Integer, IValue> heap) {
        List<Integer> allReachableAddresses = getAllReachableAddresses(referencedAddresses, heap);

        return heap.entrySet().stream()
                .filter(entry -> allReachableAddresses.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }

    private static List<Integer> getAllReachableAddresses(List<Integer> initialAddresses, Map<Integer, IValue> heap) {
        Set<Integer> reachableAddresses = new HashSet<>(initialAddresses);
        Queue<Integer> toVisit = new LinkedList<>(initialAddresses);

        while (!toVisit.isEmpty()) {
            Integer currentAddress = toVisit.poll();
            IValue value = heap.get(currentAddress);

            if (value instanceof RefValue) {
                Integer nestedAddress = ((RefValue) value).getAddress();
                if (reachableAddresses.add(nestedAddress)) {
                    toVisit.add(nestedAddress);
                }
            }
        }

        return new ArrayList<>(reachableAddresses);
    }

}
