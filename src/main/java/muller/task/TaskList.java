package muller.task;

import muller.command.MullerException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws MullerException {
        if (index < 0 || index >= tasks.size()) {
            throw new MullerException("Invalid task number!");
        }
        tasks.remove(index);
    }

    public Task get(int index) throws MullerException {
        if (index < 0 || index >= tasks.size()) {
            throw new MullerException("Invalid task number!");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
