package kobe.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks in the Kobe chatbot application.
 * Provides methods to manipulate tasks in the list, including searching by tags.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task... tasksToAdd) {
        for (Task t : tasksToAdd) {
            tasks.add(t);
        }
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword or tag.
     *
     * @param keyword The keyword or tag to search for.
     * @return A TaskList containing the matching tasks.
     */
    public TaskList findTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.name.contains(keyword) || task.hasTag(keyword))
                .collect(Collectors.toList());
        return new TaskList(matchingTasks);
    }

    /**
     * Finds tasks that contain the specified tag.
     *
     * This method filters the tasks in the task list and returns a new TaskList
     * containing only the tasks that have the given tag.
     *
     * @param tag The tag to search for in the tasks.
     * @return A TaskList containing the tasks that have the specified tag.
     */
    public TaskList findTasksByTag(String tag) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.hasTag(tag))
                .collect(Collectors.toList());
        return new TaskList(matchingTasks);
    }



    public String getAllTasksAsString() {
        if (tasks.isEmpty()) {
            return "Your task list is currently empty.";
        }
        return tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task.toString())
                .collect(Collectors.joining("\n"));
    }
}
