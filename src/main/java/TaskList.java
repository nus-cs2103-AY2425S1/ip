import java.io.*;
import java.util.*;
import task.*;

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

    public void addTask(Task task) throws IOException, InvalidTaskException {
        tasks.add(task);
        saveTasks();
    }

    public Task deleteTask(int index) throws IOException, InvalidTaskException {
        Task removedTask = tasks.remove(index);
        saveTasks();
        return removedTask;
    }

    public Task markTask(int index) throws IOException, InvalidTaskException {
        tasks.get(index).setCompletion(true);
        saveTasks();
        return tasks.get(index);
    }

    public Task unmarkTask(int index) throws IOException, InvalidTaskException {
        tasks.get(index).setCompletion(true);
        saveTasks();
        return tasks.get(index);
    }

    private List<Task> loadTasks() throws IOException, InvalidTaskException {
        return storage.loadTasks();
    }

    private void saveTasks() throws IOException, InvalidTaskException {
        storage.saveTasks(tasks);
    }
}