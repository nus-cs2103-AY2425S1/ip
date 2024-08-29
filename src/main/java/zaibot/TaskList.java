package zaibot;

import zaibot.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * This class is responsible for the
 * collection of tasks, and all the required operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {

    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Retrieves the internal task list
     *
     * @return Task list as an ArrayList of Tasks
     */
    public ArrayList<Task> retrieveTasks() {
        return tasks;
    }


    /**
     * Retrieves the list of tasks, filtered by input
     *
     * @param input Any input
     *
     * @return A filtered task list
     */
    public TaskList filterTasks(String input) {
        return new TaskList(this.tasks.stream()
                .filter(task -> task.containsInput(input))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Adds a task into the task list
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return Number of tasks in task list
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Gets the task in the task list based on the number.
     * Note that the number begins from one, unlike an index.
     *
     * @param number A number, starting from 1
     * @return The task
     */
    public Task getTask(int number) {
        return tasks.get(number - 1);
    }

    /**
     * Removes the task in the task list based on the number.
     * Note that the number begins from one, unlike an index.
     *
     * @param number A number, starting from 1
     * @return The task removed
     */
    public Task removeTask(int number) {
        return tasks.remove(number - 1);
    }

    /**
     * Marks the task in the task list based on the number.
     * Note that the number begins from one, unlike an index.
     *
     * @param number A number, starting from 1
     * @return The task
     */
    public Task markTask(int number) {
        Task task = this.getTask(number);
        task.setDone(true);
        return task;
    }

    /**
     * Unmark the task in the task list based on the number.
     * Note that the number begins from one, unlike an index.
     *
     * @param number A number, starting from 1
     * @return The task
     */
    public Task unmarkTask(int number) {
        Task task = this.getTask(number);
        task.setDone(false);
        return task;
    }

    public void clearTasks() {
        this.tasks.clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= this.getNumberOfTasks(); i++) {
            Task task = this.getTask(i);
            result.append(i).append(". ").append(task.toString()).append("\n");
        }
        return result.toString();
    }
}
