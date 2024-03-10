package model;

import exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public interface HeapInterface<K,V> {
    int add(V value) throws MyException;
    V getValueByKey(K key);
    boolean isDefined(K key);
    void update(K key, V val) throws MyException;
    void removeByKey(K key) throws MyException;
    String getOnlyKey();
    void setHeap(Map<Integer, V> heap);
    HashMap<K,V> display();
}
