package storage;

import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private List<Task> tasks;
    private static final int MAX_TASKS = 100;

    public TaskStorage() {
        tasks = new ArrayList<>(MAX_TASKS);
    }

    public void addTask(String taskDescription) {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(new Task(taskDescription));
        } // Error handling here in the fture
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return null;
        }
        return tasks.get(index);
    }
}