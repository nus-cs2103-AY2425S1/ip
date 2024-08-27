import Storage.Storage;
import Task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        Storage.save(tasks);
    }

    public Task deleteTask(int index) {
        Task removedTask = tasks.remove(index);
        Storage.save(tasks);
        return removedTask;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void saveToLocalDisk() {
        Storage.save(tasks);
    }

    @Override
    public String toString() {
        return null;
    }
}
