package storage;

import common.SkibidiException;
import storage.StorageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private List<Task> tasks;
    private static final int MAX_TASKS = 100;
    private StorageManager storageManager;

    public TaskStorage(String filePath) throws IOException {
        tasks = new ArrayList<>(MAX_TASKS);
        storageManager = new StorageManager(filePath);
        try {
            tasks = storageManager.loadTasks();
        } catch (IOException e) {
            storageManager.createFile();
        }
    }

    public void addTask(Task task) throws SkibidiException, IOException {
        if (tasks.size() >= MAX_TASKS) {
            throw new SkibidiException("Task list is full.");
        }
        tasks.add(task);
        storageManager.saveTasks(tasks);
    }

    public void deleteTask(int index) throws SkibidiException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new SkibidiException("Task index out of bounds.");
        }
        tasks.remove(index);
        storageManager.saveTasks(tasks);
    }

    public void saveTasks() throws IOException {
        storageManager.saveTasks(tasks);
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