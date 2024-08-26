package papadom.Storage;

import papadom.Exceptions.NoTaskNumberException;
import papadom.Exceptions.WrongTaskNumberException;
import papadom.tasks.Task;

import java.util.ArrayList;

/**
 * Manages the list of tasks and provides methods for task operations like adding,
 * deleting, marking, and unmarking tasks.
 */
public class TaskList {

    private final Storage STORAGE;
    private final ArrayList<Task> TASKS_LIST = new ArrayList<>();
    /**
     * Constructs a TaskList with the specified storage.
     *
     * @param storage The storage instance for saving tasks.
     */

    private final ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        this.tasks = storage.getTasks();
        this.STORAGE = storage;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks () {
        return this.TASKS_LIST;
    }

    /**
     * Outputs the current list of tasks as a formatted string.
     *
     * @return The formatted string representing the task list.
     */
    public String outputList() {
        String finalList = " Here are the tasks in your list:";
        for (int i = 0; i < this.TASKS_LIST.size(); i++) {
            Task task = this.TASKS_LIST.get(i);
            if (task == null) {
                break;
            }
            finalList += "\n " + (i + 1) + "." + task.toString();
        }
        return finalList;
    }

    /**
     * Adds a task to the list and stores it in the database.
     *
     * @param task The task to be added.
     * @return The message confirming the addition of the task.
     */
    public String addToList(Task task){
        this.TASKS_LIST.add(task);
        this.STORAGE.addTaskToDatabase(task);
        String response = " Got it. I've added this task:\n  " + task.toString() + "\n"
                + " Now you have " + (this.TASKS_LIST.size()) + " tasks in the list.";
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
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= this.TASKS_LIST.size()) {
                throw new WrongTaskNumberException();
            }
            Task taskToBeDeleted = this.TASKS_LIST.get(taskIndex);
            this.TASKS_LIST.remove(taskIndex);
            return " Noted. I've removed this task:\n  " + taskToBeDeleted + "\n Now you have " + this.TASKS_LIST.size() + " tasks in the list.";
            // Proceed with your logic using taskIndex
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException(); // Throw the custom exception if parts[1] is not an integer
        }
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
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= this.TASKS_LIST.size()) {
                throw new WrongTaskNumberException();
            }
            Task task = this.TASKS_LIST.get(taskIndex);
            task.markAsDone();
            return " Nice! I've marked this task as done:\n  " + task;
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException(); // Throw the custom exception if parts[1] is not an integer
        }
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
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= this.TASKS_LIST.size()) {
                throw new WrongTaskNumberException();
            }
            Task task = this.TASKS_LIST.get(taskIndex);
            task.unmark();
            return " OK, I've marked this task as not done yet:\n  " + task;
            // Proceed with your logic using taskIndex
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException(); // Throw the custom exception if parts[1] is not an integer
        }
    }
}