package yoda;

import yoda.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getLength() {
        return tasks.size();
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
