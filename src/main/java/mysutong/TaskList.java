package mysutong;

import mysutong.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    // Change tasks to be a List<mysutong.Task> instead of ArrayList<mysutong.Task>
    private final List<Task> tasks;

    // Constructor that accepts any List<mysutong.Task>
    public TaskList(List<Task> tasks) {
        this.tasks = tasks; // Use a general List<mysutong.Task>
    }

    // Default constructor initializes an empty ArrayList
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
