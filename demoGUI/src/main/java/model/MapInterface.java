package model;

import exceptions.MyException;

import java.util.HashMap;
import java.util.Set;

public interface MapInterface <K, V> {
    void add(K key, V value) throws MyException;
    V getValueByKey(K key);
    boolean isDefined(K key);
    void update(K key, V val) throws MyException;
    void removeByKey(K key) throws MyException;
    String getOnlyKey();
    Set<K> getOnlyKeys();
    HashMap<K,V> display();

    MapInterface<K,V> deepCopy();
}
