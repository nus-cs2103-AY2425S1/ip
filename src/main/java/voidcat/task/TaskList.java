package voidcat.task;

import voidcat.exception.VoidException;
import voidcat.ui.Ui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods for managing tasks
 * such as adding, deleting, marking, saving, and listing tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private static final String FORMAT = "\t%s%n";

    /**
     * Constructs a TaskList initialized with a list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
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
     * @throws VoidException If no tasks are found in task list.
     */
    public void listTasks() throws VoidException {
        if (tasks.isEmpty()) {
            throw new VoidException("No tasks found in list yet!");
        } else {
            Ui.showLine();
            System.out.printf(FORMAT, "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("\t%d. %s%n", i + 1, tasks.get(i));
            }
            Ui.showLine();
        }
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
     * Finds and lists tasks that match the keyword.
     *
     * @param keyword The keyword to find in description of task.
     */
    public void findTasks(String keyword) throws VoidException {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            throw new VoidException("Aww..no matching tasks found for keyword: " + keyword);
        } else {
            Ui.showLine();
            System.out.printf(FORMAT, "Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.printf("\t%d. %s%n", i + 1, matchingTasks.get(i));
            }
            Ui.showLine();
        }
    }
}
