package drbrown.utils;

import drbrown.task.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task item) {
        tasks.add(item);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public int getCount() {
        return tasks.size();
    }

    public void removeItem(int index) {
        tasks.remove(index);
    }
}
