package storage;

import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private List<String> tasks;
    private static final int MAX_TASKS = 100;

    public TaskStorage() {
        tasks = new ArrayList<>(MAX_TASKS);
    }

    public void addTask(String task) {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
        } // THROW ERROR HERE IN THE FUTURE
    }

    public List<String> getTasks() {
        return tasks;
    }
}