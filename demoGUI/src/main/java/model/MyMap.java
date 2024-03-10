package model;

import exceptions.MyException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMap<K,V> implements MapInterface<K,V>{
    Map<K,V> symMap;
    public MyMap(){
        this.symMap = new HashMap<>();
    }
    @Override
    public void add(K key, V value) throws  MyException{
        if(symMap.containsKey(key))
            throw new MyException("Key already defined!");
        symMap.put(key, value);
    }

    @Override
    public String toString() {
        return  symMap +
                "\n";
    }

    @Override
    public V getValueByKey(Object key) {
        for(K k: symMap.keySet())
            if(k == key)
                return symMap.get(k);
        return null;
    }

    @Override
    public boolean isDefined(K key) {
        return symMap.containsKey(key);
    }

    @Override
    public void update(K key, V val) throws MyException{
        boolean ok=false;
        for(K k : symMap.keySet())
            if(k == key) {
                symMap.put(key, val);
                ok = true;
            }
        if(!ok)
            throw new MyException("Key is not defined.");
    }
    public void removeByKey(K key) throws MyException {
        if(!symMap.containsKey(key))
            throw new MyException("Key not defined!");
        symMap.remove(key);
    }

    @Override
    public String getOnlyKey() {
        return symMap.keySet().toString();
    }

    @Override
    public Set<K> getOnlyKeys() {
        return symMap.keySet();
    }

    @Override
    public HashMap<K, V> display() {
        return (HashMap<K, V>) symMap;
    }

    @Override
    public MapInterface<K, V> deepCopy() {
        MapInterface<K,V> clone = new MyMap<>();
        for(K key : this.symMap.keySet()) {
            try {
                clone.add(key, this.symMap.get(key));
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
        return clone;
    }


}
