package LittleMissHelpful;
import java.util.ArrayList;

import LittleMissHelpful.Exception.InvalidTaskFormatException;
import LittleMissHelpful.Task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) throws InvalidTaskFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Task number out of range.");
        }
        tasks.remove(index);
    }

    public Task get(int index) throws InvalidTaskFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Task number out of range.");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTask(int index) throws InvalidTaskFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Task number out of range.");
        }
        Task t = tasks.get(index).markTask();
        tasks.set(index, t);
    }

    public void unmarkTask(int index) throws InvalidTaskFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Task number out of range.");
        }
        Task t = tasks.get(index).unmarkTask();
        tasks.set(index, t);
    }
}
