package seanbot;

import java.util.ArrayList;
import java.util.List;

import seanbot.Tasks.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    public void deleteTask(int index) {
        assert index > 0 && index <= tasks.size() : "Invalid index";
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

/**
     * Finds and returns a list of tasks that match the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Keyword cannot be null or empty";
        List<Task> found = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                found.add(task);
            }
        }
        return found;
    }
}
