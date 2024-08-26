package bopes.task;

import java.util.ArrayList;
import bopes.exception.BopesException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws BopesException {
        if (index < 0 || index >= tasks.size()) {
            throw new BopesException("Error: The task index is out of range.");
        }
        tasks.remove(index);
    }

    public Task markTask(int index) throws BopesException {
        if (index < 0 || index >= tasks.size()) {
            throw new BopesException("Error: The task index is out of range.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) throws BopesException {
        if (index < 0 || index >= tasks.size()) {
            throw new BopesException("Error: The task index is out of range.");
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
