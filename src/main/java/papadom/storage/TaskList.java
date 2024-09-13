package papadom.storage;

import java.util.ArrayList;
import papadom.exceptions.NoTaskNumberException;
import papadom.exceptions.WrongTaskNumberException;
import papadom.tasks.Task;

/**
 * Manages the list of tasks and provides methods for task operations like adding,
 * deleting, marking, and unmarking tasks.
 */
public class TaskList {

    private final Storage STORAGE;
    /**
     * Constructs a TaskList with the specified storage.
     *
     * @param storage The storage instance for saving tasks.
     */


    public TaskList(Storage storage) {
        assert storage != null : "Storage cannot be null";
        this.STORAGE = storage;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return Storage.TASK_LIST;
    }

    /**
     * Outputs the current list of tasks as a formatted string.
     *
     * @return The formatted string representing the task list.
     */
    public String outputList() {
        String finalList = " Here are the tasks in your list:";
        for (int i = 0; i < Storage.TASK_LIST.size(); i++) {
            Task task = Storage.TASK_LIST.get(i);
            if (task == null) {
                break;
            }
            finalList += "\n  " + (i + 1) + ". " + task;
        }
        return finalList;
    }

    private int extractTaskIndex(String text) throws NoTaskNumberException {
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException();
        }
    }

    /**
     * Adds a task to the list and stores it in the database.
     *
     * @param task The task to be added.
     * @return The message confirming the addition of the task.
     */
    public String addToList(Task task) {
        assert task != null : "Task cannot be null";
        Storage.TASK_LIST.add(task);
        this.STORAGE.addTaskToDatabase(task);
        String response = " Got it. I've added this task:\n  " + task.toString() + "\n"
                + " Now you have " + (Storage.TASK_LIST.size()) + " tasks in the list.";
        return response;
    }

    /**
     * Deletes a task from the list based on the provided command text.
     *
     * @param text The command text specifying the task number to be deleted.
     * @return The message confirming the deletion of the task.
     * @throws NoTaskNumberException     If the task number is missing or invalid.
     * @throws WrongTaskNumberException  If the task number does not exist.
     */
    public String deleteEvent(String text) throws NoTaskNumberException, WrongTaskNumberException {
        int taskIndex = extractTaskIndex(text);
        if (taskIndex >= Storage.TASK_LIST.size()) {
            throw new WrongTaskNumberException();
        }
        Task taskToBeDeleted = Storage.TASK_LIST.get(taskIndex);
        Storage.TASK_LIST.remove(taskIndex);
        return " Noted. I've removed this task:\n  " + taskToBeDeleted
                + "\n Now you have " + Storage.TASK_LIST.size() + " tasks in the list.";
            // Proceed with your logic using taskIndex
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
        int taskIndex = extractTaskIndex(text);
        if (taskIndex >= Storage.TASK_LIST.size()) {
            throw new WrongTaskNumberException();
        }
        Task task = Storage.TASK_LIST.get(taskIndex);
        task.markAsDone();
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
        int taskIndex = extractTaskIndex(text);
        if (taskIndex >= Storage.TASK_LIST.size()) {
            throw new WrongTaskNumberException();
        }
        Task task = Storage.TASK_LIST.get(taskIndex);
        task.unmark();
        return " OK, I've marked this task as not done yet:\n  " + task;
    }
}