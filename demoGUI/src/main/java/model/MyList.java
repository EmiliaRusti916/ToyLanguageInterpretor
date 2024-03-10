package model;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements ListInterface<T>{

    List<T> list;
    public MyList(){
        this.list = new ArrayList<>();
    }

    @Override
    public String toString() {
        return list.toString() + "\n";
    }

    @Override
    public void add(T element) {
        this.list.add(element);
    }

    @Override
    public ArrayList<T> display() {
        return (ArrayList<T>) this.list;
    }
}
