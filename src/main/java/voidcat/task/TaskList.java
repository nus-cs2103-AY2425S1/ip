package voidcat.task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import voidcat.exception.VoidCatException;

/**
 * Represents a list of tasks and provides methods for managing tasks
 * such as adding, deleting, marking, saving, and listing tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList initialized with a list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to remove.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark.
     */
    public Task markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    /**
     * Unmarks a task as done (sets it as not done).
     *
     * @param index The index of the task to unmark.
     */
    public Task unmarkTaskAsDone(int index) {
        tasks.get(index).unmarkAsDone();
        return tasks.get(index);
    }

    /**
     * Lists the tasks in order of addition of the task to the task list.
     *
     * @throws VoidCatException If no tasks are found in task list.
     */
    public String listTasks() throws VoidCatException {
        if (this.tasks.isEmpty()) {
            throw new VoidCatException("No saved tasks found yet! Task list is empty."
                    + "\nStart adding tasks and track them!");
        }
        String responseList = "Here are the tasks from your list ^•ﻌ•^ฅ♡ :\n\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            responseList += (i + 1) + ". " + this.tasks.get(i) + "\n";
        }
        return responseList;
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Saves and writes the tasks to a file in the order of addition
     * of the task to the task list.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasks(BufferedWriter bw) throws IOException {
        for (Task task : tasks) {
            bw.write(task.toSaveFormat());
            bw.newLine();
        }
        bw.close();
    }

    /**
     * Finds and lists tasks that match the keyword using partial and case-insensitive search.
     *
     * @param keyword The keyword to find in the description of tasks.
     * @return The formatted string of matching tasks.
     * @throws VoidCatException If no matching tasks are found.
     */
    public String findTasks(String keyword) throws VoidCatException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase(Locale.ROOT);

        for (Task task : tasks) {
            String lowerCaseDescription = task.description.toLowerCase(Locale.ROOT);
            boolean hasKeyword = lowerCaseDescription.contains(lowerCaseKeyword);
            if (!hasKeyword) {
                continue;
            }
            matchingTasks.add(task);
        }

        if (matchingTasks.isEmpty()) {
            throw new VoidCatException("Aww..no matching tasks found for keyword: " + keyword);
        }
        TaskList matchTaskList = new TaskList(matchingTasks);
        return matchTaskList.listTasks();
    }
}
