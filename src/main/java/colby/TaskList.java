package colby;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markTaskAsUndone(int index) {
        tasks.get(index).markAsUndone();
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
