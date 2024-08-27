package com.Nimbus;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> arr;

    public TaskList() {
        arr = new ArrayList<>();
    }

    public void add(Task task) {
        arr.add(task);
    }

    public void add(TaskList tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            arr.add(tasks.get(i));
        }
    }

    public Task remove(int index) {
        return arr.remove(index);
    }

    public void mark(int index) {
        arr.get(index).setDone();
    }

    public void unmark(int index) {
        arr.get(index).setNotDone();
    }

    public int size() {
        return arr.size();
    }

    public Task get(int index) {
        return arr.get(index);
    }
}
