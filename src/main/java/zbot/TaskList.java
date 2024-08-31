package zbot;

import java.util.ArrayList;
import java.util.Iterator;

import zbot.task.Task;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public boolean add(Task task) {
        return this.list.add(task);
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public Task remove(int index) {
        return this.list.remove(index);
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return this.list.iterator();
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
