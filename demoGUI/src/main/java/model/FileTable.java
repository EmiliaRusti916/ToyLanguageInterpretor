package model;

import exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class FileTable<K,V> implements FileTableInterface<K,V>{
    Map<K,V> fileTbl;

    public FileTable() {
        this.fileTbl = new HashMap<>();
    }

    @Override
    public void add(K key, V value) throws  MyException{
        if(fileTbl.containsKey(key))
            throw new MyException("Key already defined!");
        fileTbl.put(key, value);
    }

    @Override
    public String toString() {
        return this.fileTbl +
                "\n";
    }

    @Override
    public V getValueByKey(Object key) {
        for(K k: fileTbl.keySet())
            if(k == key)
                return fileTbl.get(k);
        return null;
    }

    @Override
    public boolean isDefined(String key) {
        return fileTbl.containsKey(key);
    }

    @Override
    public void update(K key, V val) throws MyException{
        boolean ok=false;
        for(K k : fileTbl.keySet())
            if(k == key) {
                fileTbl.put(key, val);
                ok = true;
            }
        if(!ok)
            throw new MyException("Key is not defined.");
    }

    @Override
    public void removeByKey(K key) throws MyException {
        if(!fileTbl.containsKey(key))
            throw new MyException("Key not defined!");
        fileTbl.remove(key);
    }

    @Override
    public String getOnlyKey() {
        return fileTbl.keySet().toString() + '\n';
    }

    @Override
    public HashMap<K, V> display() {
        return (HashMap<K, V>) fileTbl;
    }
}
