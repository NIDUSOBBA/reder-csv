package org.example.result;

import java.util.ArrayList;

public class AbstractResult<T>{
    private final ArrayList<T> items;

    public AbstractResult() {
        items = new ArrayList<>();
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public void addItems(T items) {
        this.items.add(items);
    }

}
