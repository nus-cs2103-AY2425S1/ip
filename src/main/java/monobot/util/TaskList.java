package monobot.util;

import monobot.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTask(int index) {
        tasks.get(index).markTask();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmarkTask();
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public TaskList filterTasks(String filter) {
        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(task -> task.toString().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(filteredTasks);
    }
}
