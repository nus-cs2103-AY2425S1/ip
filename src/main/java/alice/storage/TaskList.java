package alice.storage;

import alice.task.*;
import java.io.*;
import java.util.*;

public class TaskList {
    private final Storage storage;
    private List<Task> tasks;

    public TaskList(Storage storage) {
        this.storage = storage;
        try {
            this.tasks = loadTasks();
        } catch (IOException | InvalidTaskException e) {
            this.tasks = new ArrayList<>();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) throws IOException {
        tasks.add(task);
        saveTasks();
    }

    public Task deleteTask(int index) throws IOException {
        Task removedTask = tasks.remove(index);
        saveTasks();
        return removedTask;
    }

    public Task markTask(int index) throws IOException {
        tasks.get(index).setCompletion(true);
        saveTasks();
        return tasks.get(index);
    }

    public Task unmarkTask(int index) throws IOException {
        tasks.get(index).setCompletion(false);
        saveTasks();
        return tasks.get(index);
    }

    private List<Task> loadTasks() throws IOException, InvalidTaskException {
        return storage.loadTasks();
    }

    private void saveTasks() throws IOException {
        storage.saveTasks(tasks);
    }
}