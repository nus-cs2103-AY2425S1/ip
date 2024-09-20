package rex.util;

import java.util.Scanner;

import rex.command.Command;
import rex.exception.InvalidInputException;
import rex.task.Task;
import rex.task.TaskList;

/**
 * Handles the user interface of the application.
 * It provides methods to interact with the user by reading user inputs,
 * displaying messages, prompts, and error messages.
 */
public class Ui {
    // "rawr" string added to end of each print statement
    private static final String RAWR = "rawr";

    // "RAWRRRR" that comes with each error message
    private static final String ERROR_PREFIX = "RAWRRRR!!!";

    // Scanner placeholder
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} instance and initializes the {@code Scanner}.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     *
     * @return A string representing the greeting message.
     */
    public static String getGreeting() {
        System.out.println("Hello! I'm Rex! " + RAWR);
        System.out.println("What can I do for you? " + RAWR);
        return "Hello! I'm Rex! " + RAWR + "\nWhat can I do for you? " + RAWR;
    }

    /**
     * Displays a help message listing available commands.
     *
     * @return A string containing the help message with the list of commands.
     */
    public String getHelp() {
        return Command.getCommandList();
    }

    /**
     * Displays the current list of tasks.
     *
     * @param taskList The {@code TaskList} containing the user's tasks.
     * @return A string representing the list of tasks.
     */
    public String displayList(TaskList taskList) {
        return taskList.getListDisplay();
    }

    /**
     * Displays tasks that are scheduled for a specific date.
     *
     * @param taskList The {@code TaskList} containing the user's tasks.
     * @param date The date for which the schedule should be displayed.
     * @return A string containing the list of scheduled tasks for the given date.
     */
    public String displaySchedule(TaskList taskList, String date) {
        return taskList.getSchedule(date);
    }

    /**
     * Displays the tasks that match the keyword searched by the user.
     *
     * @param taskList The {@code TaskList} containing the user's tasks.
     * @param keyword The keyword to search for in the task list.
     * @return A string containing the matching tasks.
     */
    public String findTask(TaskList taskList, String keyword) {
        return taskList.findTasks(keyword);
    }

    /**
     * Informs the user that a new task has been added.
     *
     * @param task The {@code Task} that was added to the list.
     * @return A string confirming that the task was added, along with the updated task count.
     */
    public String addTask(Task task) {
        String output = "Got it. I've added this task:\n";
        output += "  " + task;
        output += "\nNow you have " + Task.getNumberOfTasks() + " tasks in the list.";
        return output;
    }

    /**
     * Informs the user that a task has been marked as done.
     *
     * @param task The {@code Task} that was marked as done.
     * @return A string confirming that the task has been marked as done.
     */
    public String markTask(Task task) {
        String output = "Nice! I've marked this task as done:\n";
        output += "  " + task;
        return output;
    }

    /**
     * Informs the user that a task has been marked as not done.
     *
     * @param task The {@code Task} that was marked as not done.
     * @return A string confirming that the task has been marked as not done.
     */
    public String unmarkTask(Task task) {
        String output = "OK, I've marked this task as not done yet:\n";
        output += "  " + task;
        return output;
    }

    /**
     * Informs the user that a task has been deleted.
     *
     * @param task The {@code Task} that was removed from the list.
     * @return A string confirming that the task was deleted, along with the updated task count.
     */
    public String deleteTask(Task task) {
        String output = "Noted. I've removed this task:\n";
        output += "  " + task;
        output += "\nNow you have " + Task.getNumberOfTasks() + " tasks in the list.";
        return output;
    }

    /**
     * Prints a playful "rawr" message to the user.
     *
     * @return A string with the "rawr" message.
     */
    public String rawr() {
        return RAWR + "!";
    }

    /**
     * Displays a goodbye message and closes the {@code Scanner}.
     *
     * @return A string containing the goodbye message.
     */
    public String getGoodbye() {
        scanner.close();
        return "Bye. Hope to see you again soon! " + RAWR;
    }

    /**
     * Displays an error message to the user with a default error prefix.
     *
     * @param e The {@code InvalidInputException} containing the error message.
     * @return A string containing the error message with the error prefix.
     */
    public String errorMessage(InvalidInputException e) {
        return errorMessage(e.getMessage());
    }

    /**
     * Displays a custom error message to the user with a default error prefix.
     *
     * @param message The error message to be displayed.
     * @return A string containing the error message with the error prefix.
     */
    public String errorMessage(String message) {
        return ERROR_PREFIX + " " + message;
    }
}
