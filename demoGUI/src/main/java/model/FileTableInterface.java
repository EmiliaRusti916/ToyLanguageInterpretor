package model;

import exceptions.MyException;

import java.util.HashMap;

public interface FileTableInterface<K,V>{
    void add(K key, V value) throws MyException;
    V getValueByKey(K key);
    boolean isDefined(String key);
    void update(K key, V val) throws MyException;
    void removeByKey(K key) throws MyException;
    String getOnlyKey();
    HashMap<K,V> display();
}
