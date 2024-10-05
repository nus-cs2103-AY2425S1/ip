package shrimp.utility;

import java.util.Scanner;

import shrimp.task.Task;
import shrimp.task.TaskList;

/**
 * The {@code Ui} class handles interactions with the user.
 * It provides methods to display various messages and prompt the user for input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} instance.
     * Initializes the scanner to read user input from the console.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns a welcome message to greet the user.
     *
     * @return A welcome message as a {@code String}.
     */
    public String printWelcome() {
        String greetings = "Domo! Same desu~ I am shrimp, and I am happy to assist you! Hewwo? <3";
        return greetings;
    }

    /**
     * Returns an exit message to be displayed when the user ends the session.
     *
     * @return An exit message as a {@code String}.
     */
    public String printExit() {
        String exit = "Byebye~ It's time to say goodbye for the day~ Hope you enjoyed and had fuuun~ "
                + "I'll see you later~";
        return exit;
    }

    /**
     * Returns a formatted string listing all tasks in the provided {@code TaskList}.
     * Each task is displayed with an index.
     *
     * @param taskList The {@code TaskList} containing the tasks to be displayed.
     * @return A formatted string with all tasks and the task count.
     */
    public String printTaskList(TaskList taskList) {
        String output = "Gotchaaa~ Here's the list so far:";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            output += String.format("\n    %s. %s", i + 1, task);
        }
        output = output + printTaskCount(taskList);
        return output;
    }

    /**
     * Returns a string indicating the number of tasks in the provided {@code TaskList}.
     *
     * @param taskList The {@code TaskList} containing the tasks to be counted.
     * @return A string with the task count.
     */
    private String printTaskCount(TaskList taskList) {
        return String.format("\nLemme count~ You now have %s item(s) in your list!%n", taskList.size());
    }

    /**
     * Returns a string indicating that a task has been marked as complete.
     *
     * @param task The {@code Task} that was marked as complete.
     * @return A string confirming that the task is marked complete.
     */
    public String printMark(Task task) {
        String output = "heya~ I've checked this task as complete! Feels good, right?";
        output = output + "\n    " + task;
        return output;
    }

    /**
     * Returns a string indicating that a task has been unmarked (marked as incomplete).
     *
     * @param task The {@code Task} that was unmarked.
     * @return A string confirming that the task is marked incomplete.
     */
    public String printUnmark(Task task) {
        String output = "Whoops~ I've unchecked the task as incomplete! Be careful next time~";
        output = output + "\n    " + task;
        return output;
    }

    /**
     * Returns a string indicating that a task has been deleted and displays the updated task count.
     *
     * @param task     The {@code Task} that was deleted.
     * @param taskList The {@code TaskList} after the task was deleted.
     * @return A string confirming the task deletion and displaying the task count.
     */
    public String printDelete(Task task, TaskList taskList) {
        String output = "Done! The task has been deleted!";
        output = output + "\n    " + task;
        output += printTaskCount(taskList);
        return output;
    }

    /**
     * Returns a string indicating that a task has been added and displays the updated task count.
     *
     * @param task     The {@code Task} that was added.
     * @param taskList The {@code TaskList} after the task was added.
     * @return A string confirming the task addition and displaying the task count.
     */
    public String printAdd(Task task, TaskList taskList) {
        String output = "rawr! '" + task + "' has been added to the list~";
        output += printTaskCount(taskList);
        return output;
    }

    /**
     * Returns a formatted string that lists all tasks in the provided {@code TaskList} that match the search criteria.
     * <p>The returned string starts with a message indicating that matching tasks have been found, followed
     * by an ordered list of tasks.</p>
     *
     * @param taskList The {@code TaskList} containing the tasks that match the search criteria.
     * @return A formatted string displaying all matching tasks, each prefixed by its position in the list.
     */
    public String printFind(TaskList taskList) {
        String output = "Heya~ Here's all the tasks I found matches your description~";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            output += String.format("\n    %s.%s", i + 1, task);
        }
        return output;
    }


    /**
     * Returns a formatted string displaying an error message.
     *
     * @param message The error message to be displayed.
     * @return A formatted error message as a {@code String}.
     */
    public String printError(String message) {
        return "Oh nyoo~ " + message;
    }
}
