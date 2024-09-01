package drbrown.utils;

import drbrown.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int count = 0;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task item) {
        this.tasks.add(item);
        this.count++;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public int getCount() {
        return this.count;
    }

    public void removeItem(int count) {
        this.tasks.remove(count);
    }

}
