package Main;

import Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = tasks;
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Add task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Remove task
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    // Retrieves task
    public Task get(int index) {
        return tasks.get(index);
    }

    // Number of tasks
    public int size() {
        return tasks.size();
    }

    // List of all tasks
    public List<Task> getTasks() {
        return tasks;
    }
}
