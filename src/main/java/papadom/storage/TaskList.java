package papadom.storage;

import java.util.List;

import papadom.exceptions.NoTaskNumberException;
import papadom.exceptions.WrongTaskNumberException;
import papadom.tasks.Task;
import papadom.utils.Parser;

/**
 * Manages the list of tasks and provides methods for task operations like adding,
 * deleting, marking, and unmarking tasks.
 */
public class TaskList {
    private final Storage STORAGE;
    protected final List<Task> TASK_LIST; // ArrayList to store tasks

    /**
     * Constructs a TaskList with the specified storage.
     *
     * @param storage The storage instance for saving tasks.
     */
    public TaskList(Storage storage) {
        assert storage != null : "Storage cannot be null";
        this.STORAGE = storage;
        this.TASK_LIST = STORAGE.loadTasks(); // Load tasks at the start of the session
    }

    /**
     * Outputs the current list of tasks as a formatted string.
     *
     * @return The formatted string representing the task list.
     */
    public String outputList() {
        if (this.TASK_LIST.isEmpty()) {
            return " There are no tasks in your list.";
        }

        StringBuilder finalList = new StringBuilder(" Here are the tasks in your list:");
        for (int i = 0; i < this.TASK_LIST.size(); i++) {
            finalList.append("\n  ").append(i + 1).append(". ").append(this.TASK_LIST.get(i).toString());
        }
        return finalList.toString();
    }

    /**
     * Adds a task to the list and stores it in the database.
     *
     * @param task The task to be added.
     * @return The message confirming the addition of the task.
     */
    public String addToList(Task task) {
        assert task != null : "Task cannot be null";

        this.TASK_LIST.add(task); // Add task to the ArrayList
        STORAGE.addTaskToDatabase(task); // Append the task to the file

        return " Got it. I've added this task:\n  " + task.toString() + "\n"
                + " Now you have " + this.TASK_LIST.size() + " tasks in the list.";
    }

    /**
     * Deletes a task from the list based on the provided command text.
     *
     * @param text The command text specifying the task number to be deleted.
     * @return The message confirming the deletion of the task.
     * @throws NoTaskNumberException     If the task number is missing or invalid.
     * @throws WrongTaskNumberException  If the task number does not exist.
     */
    public String deleteTask(String text) throws NoTaskNumberException, WrongTaskNumberException {
        int taskIndex = Parser.extractTaskIndex(text); // Get the task index from the input

        if (taskIndex >= this.TASK_LIST.size()) {
            throw new WrongTaskNumberException();
        }

        Task taskToBeDeleted = this.TASK_LIST.remove(taskIndex); // Remove the task from the list
        STORAGE.updateTasksInFile(this); // Update the file with the modified task list

        return " Noted. I've removed this task:\n  " + taskToBeDeleted
                + "\n Now you have " + this.TASK_LIST.size() + " tasks in the list.";
    }

    /**
     * Marks a task as completed based on the provided command text.
     *
     * @param text The command text specifying the task number to be marked.
     * @return The message confirming the task has been marked.
     * @throws NoTaskNumberException     If the task number is missing or invalid.
     * @throws WrongTaskNumberException  If the task number does not exist.
     */
    public String markTask(String text) throws NoTaskNumberException, WrongTaskNumberException {
        int taskIndex = Parser.extractTaskIndex(text); // Get the task index from the input

        if (taskIndex >= this.TASK_LIST.size()) {
            throw new WrongTaskNumberException();
        }

        Task task = this.TASK_LIST.get(taskIndex);
        task.markAsDone(); // Mark the task as done
        STORAGE.updateTasksInFile(this); // Update the file with the modified task list

        return " Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Unmarks a task as incomplete based on the provided command text.
     *
     * @param text The command text specifying the task number to be unmarked.
     * @return The message confirming the task has been unmarked.
     * @throws NoTaskNumberException     If the task number is missing or invalid.
     * @throws WrongTaskNumberException  If the task number does not exist.
     */
    public String unmarkTask(String text) throws NoTaskNumberException, WrongTaskNumberException {
        int taskIndex = Parser.extractTaskIndex(text); // Get the task index from the input

        if (taskIndex >= this.TASK_LIST.size()) {
            throw new WrongTaskNumberException();
        }

        Task task = this.TASK_LIST.get(taskIndex);
        task.unmark(); // Unmark the task
        STORAGE.updateTasksInFile(this); // Update the file with the modified task list

        return " OK, I've marked this task as not done yet:\n  " + task;
    }
}
