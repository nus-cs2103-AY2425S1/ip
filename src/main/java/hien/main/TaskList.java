package hien.main;
import hien.exception.HienException;
import hien.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws HienException {
        if (index < 0 || index >= tasks.size()) {
            throw new HienException("Task index is out of bounds.");
        }
        tasks.remove(index);
    }

    public void markTask(int index, boolean isDone) throws HienException {
        if (index < 0 || index >= tasks.size()) {
            throw new HienException("Task index is out of bounds.");
        } else {
            Task task = tasks.get(index);
            if (isDone) {
                task.markAsDone();
            } else {
                task.markAsUndone();
            }
        }
    }

    public Task getTask(int index) throws HienException {
        if (index < 0 || index >= tasks.size()) {
            throw new HienException("Task index is out of bounds.");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}

