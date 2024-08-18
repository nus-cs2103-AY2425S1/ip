package storage;

import common.SkibidiException;

import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private List<Task> tasks;
    private static final int MAX_TASKS = 100;

    public TaskStorage() {
        tasks = new ArrayList<>(MAX_TASKS);
    }

    public void addTask(Task task) throws SkibidiException {
        if (tasks.size() >= MAX_TASKS) {
            throw new SkibidiException("Task list is full.");
        }
        tasks.add(task);
    }

    public void deleteTask(int index) throws SkibidiException {
        if (index < 0 || index >= tasks.size()) {
            throw new SkibidiException("Task index out of bounds.");
        }
        tasks.remove(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return null;
        }
        return tasks.get(index);
    }
}