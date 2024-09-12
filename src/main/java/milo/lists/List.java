package milo.lists;

import milo.tasks.Task;

import java.util.ArrayList;

abstract public class List<T> {

    public abstract void add(T x);

    public abstract T get(int index);

    public abstract void remove(int index);
}
