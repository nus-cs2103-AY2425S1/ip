package froggy;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TaskList} class manages a collection of tasks.
 * It provides methods to add, remove, and search for tasks,
 * as well as to mark tasks as done or undone.
 */
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void printTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
    }

    public String getTasksToString() {
        String output = "";
        for (int i = 1; i <= tasks.size(); i++) {
            output = output + i + "." + tasks.get(i - 1).toString() + "\n";
        }
        return output;
    }

    public void printTask(int index) {
        System.out.println(tasks.get(index).toString());
    }

    public void setDone(int index, boolean isDone) {
        tasks.get(index).setStatus(isDone);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void add(Task task, Storage storage) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    public List<Task> searchTasks(String searchInput) {
        List<Task> output = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(searchInput.toLowerCase())) {
                output.add(task);
            }
        }
        return output;
    }

    public boolean isDuplicate(Task task) {
        for (Task currentTask : tasks) {
            if (task.equals(currentTask)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInRange(int index) {
        return index >= 0 && index < tasks.size();
    }

}
