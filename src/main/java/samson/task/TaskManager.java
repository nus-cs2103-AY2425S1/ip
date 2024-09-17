package samson.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import samson.SamException;
import samson.Storage;

/**
 * The <code> TaskManager </code> class is responsible for managing tasks and interacting with storage.
 * It provides methods to add, list, mark, unmark, and delete tasks, as well as to save tasks to a file.
 */
public class TaskManager {
    private int taskCount;
    private List<Task> tasks;
    private Storage storage;
    private boolean istaskStatus;

    /**
     * Constructs a <code> TaskManager </code> with the specified storage.
     * It attempts to load tasks from the storage file.
     *
     * @param storage The storage object used to save and load tasks.
     */
    public TaskManager(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;

        try {
            this.tasks = storage.loadTaskFromFile();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        } catch (SamException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a task to the task list and saves the updated list to the storage file.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of tasks.
     */
    public void listTask() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks a task as completed by its index.
     *
     * @param num The index of the task to be marked as done.
     */
    public void markTask(int num) {
        tasks.get(num - 1).complete();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks.get(num - 1).toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Unmarks a task as incomplete by its index.
     *
     * @param num The index of the task to be marked as not done.
     */
    public void unmarkTask(int num) {
        tasks.get(num - 1).notComplete();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks.get(num - 1).toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Deletes a task by its index and saves the updated list to the storage file.
     *
     * @param num The index of the task to be deleted.
     */
    public void deleteTask(int num) {
        Task removedTask = tasks.remove(num - 1);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask.toString());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int checkSize() {
        return tasks.size();
    }

    /**
     * Saves the current list of tasks to the storage file.
     */
    private void saveTasks() {
        try {
            storage.saveTasksToFile(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Checks the status of a task.
     *
     * @return "X" if the task is completed, otherwise {@code " "}.
     */
    public String checkStatus() {
        return istaskStatus ? "X" : " ";
    }
}
