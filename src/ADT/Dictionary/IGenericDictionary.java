package ADT.Dictionary;

import java.util.List;
import java.util.Map;
import Exception.KeyNotFoundException;

public interface IGenericDictionary<K, V> {

    void insert(K key, V value);

    boolean isDefined(K key);

    V lookup(K key) throws KeyNotFoundException;

    void delete(K key) throws KeyNotFoundException;

    Map<K, V> getMap();

    public void setMap(Map<K, V> map);

    List<K> getKeys();

    boolean exists(K key);
}