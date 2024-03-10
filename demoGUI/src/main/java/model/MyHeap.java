package model;

import exceptions.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<V> implements HeapInterface<Integer,V>{
    Map<Integer,V> myHeap;
    int freeLocation;

    public MyHeap() {
        this.myHeap = new HashMap<>();
        this.freeLocation = 1;
    }

    @Override
    public int add(V value) throws MyException {
        if(myHeap.containsKey(freeLocation))
            throw new MyException("Key already defined in heap!");
        myHeap.put(this.freeLocation, value);
        this.freeLocation++;
        return this.freeLocation-1;
    }

    @Override
    public V getValueByKey(Integer key) {
        for(Integer k: myHeap.keySet())
            if(k == key)
                return myHeap.get(k);
        return null;
    }

    @Override
    public boolean isDefined(Integer key) {
        return myHeap.containsKey(key);
    }

    @Override
    public void update(Integer key, V val) throws MyException {
        if(!myHeap.containsKey(key))
            throw  new MyException("Key not defined in heap!");
        myHeap.put(key,val);
    }

    @Override
    public void removeByKey(Integer key) throws MyException {
        if(!myHeap.containsKey(key))
            throw new MyException("Key not defined!");
        myHeap.remove(key);
    }

    @Override
    public String getOnlyKey() {
        return myHeap.keySet().toString();
    }

    @Override
    public void setHeap(Map<Integer, V> heap) {
        this.myHeap = heap;
    }

    @Override
    public HashMap<Integer, V> display() {
        return (HashMap<Integer, V>) myHeap;
    }

    @Override
    public String toString() {
        String rep = "Heap: \n[";
        Collection<Integer> keys = myHeap.keySet();
        for(Integer key : keys)
        {
            rep += (key.toString() + "->" + myHeap.get(key) + " ");
        }
        rep += "]";
        return rep;
    }
}
