package jag;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * TaskList Class that encapsulates all the properties and methods
 * required to store, access and edit any given list of tasks made by
 * the user
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private int size;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Finds and returns all tasks that contains the given search parameter
     *
     * @param searchDescription String that user wants to find amongst TaskList
     * @return TaskList instance with found tasks or just an empty instance
     */
    public TaskList findTask(String searchDescription) {
        TaskList matchingTasks = new TaskList();
        this.tasks.stream()
                .filter(task -> task.getDescription().contains(searchDescription))
                .forEach(matchingTasks::addTask);
        return matchingTasks;
    }

    /**
     * A Custom constructor for the TaskList class that creates and sets
     * an instance of ArrayList, which will be used to store any given list
     * of tasks
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

}
