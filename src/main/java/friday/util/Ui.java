package friday.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import friday.task.Task;

/**
 * The Ui class handles all interactions with the user.
 * It provides methods to display messages, read commands, and show task-related information.
 */
public class Ui {
    private static final String DISPLAY_FORMAT_PATTERN = "MMM dd yyyy";

    /**
     * Prints a separation line to separate different sections of the output.
     */
    public void printSeparation() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message with a separation line above and below it.
     *
     * @param message The message to be displayed.
     */
    public void printMessage(String message) {
        printSeparation();
        System.out.println(message);
        printSeparation();
    }

    /**
     * Greets the user with a welcome message.
     *
     * @return The greeting message as a String.
     */
    public String greet() {
        String greeting = "     Hello! I'm Friday\n"
                + "     What can I do for you?";

        printMessage(greeting);

        return greeting;
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     * @return The error message as a String.
     */
    public String showError(String message) {
        String error = "     " + message;

        printMessage(error);

        return message;
    }

    /**
     * Reads the user's input command.
     *
     * @return The command entered by the user as a String.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays an error message indicating a loading failure.
     *
     * @return The loading error message as a String.
     */
    public String showLoadingError() {
        String loadingError = "     Error loading tasks from file. Starting with an empty task list.";

        printMessage(loadingError);

        return loadingError;
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task      The task that was added.
     * @param noOfTasks The current number of tasks in the list.
     * @return The message indicating the task was added as a String.
     */
    public String showAddedTask(Task task, int noOfTasks) {
        String message = "     Got it. I've added this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + noOfTasks + " tasks in the list.";

        printMessage(message);

        return message;
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param task      The task that was deleted.
     * @param noOfTasks The current number of tasks remaining in the list.
     * @return The message indicating the task was deleted as a String.
     */
    public String showDeletedTask(Task task, int noOfTasks) {
        String message = "     Noted. I've removed this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + noOfTasks + " tasks in the list.";

        printMessage(message);

        return message;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return The message indicating the task was marked as done as a String.
     */
    public String showMarkedTask(Task task) {
        String message = "     Nice! I've marked this task as done:\n"
                + "       " + task;

        printMessage(message);

        return message;
    }

    /**
     * Displays a message indicating that a task has been unmarked as done.
     *
     * @param task The task that was unmarked as done.
     * @return The message indicating the task was unmarked as done as a String.
     */
    public String showUnmarkedTask(Task task) {
        String message = "     Nice! I've unmarked this task:\n"
                + "       " + task;

        printMessage(message);

        return message;
    }

    /**
     * Displays the list of all tasks.
     *
     * @param tasks The TaskList containing all tasks to be displayed.
     * @return The list of tasks as a String.
     */
    public String showTasks(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        if (tasks.isTaskListEmpty()) {
            output.append("     Your task list is empty.\n");
        } else {
            output.append("     Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("     ").append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
            }
        }

        printMessage(output.toString());

        return output.toString();
    }

    /**
     * Displays the list of tasks occurring on a specific date.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     * @param date  The date for which tasks need to be listed.
     * @return The list of tasks on the specified date as a String.
     */
    public String showSpecificTasks(TaskList tasks, LocalDate date) {
        StringBuilder output = new StringBuilder();
        if (tasks.isTaskListEmpty()) {
            output.append("     No tasks found on this date.\n");
        } else {
            output.append("     Here are your tasks on ")
                    .append(date.format(DateTimeFormatter.ofPattern(DISPLAY_FORMAT_PATTERN)))
                    .append(":\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("     ").append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
            }
        }

        printMessage(output.toString());

        return output.toString();
    }

    /**
     * Displays the list of tasks that match the search criteria.
     *
     * @param tasks The TaskList containing the matching tasks to be displayed.
     * @return The list of matching tasks as a String.
     */
    public String showMatchingTasks(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        if (tasks.isTaskListEmpty()) {
            output.append("     No matching tasks found.\n");
        } else {
            output.append("     Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("     ").append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
            }
        }

        printMessage(output.toString());

        return output.toString();
    }

    /**
     * Displays a goodbye message when the user exits the application.
     *
     * @return The goodbye message as a String.
     */
    public String sayGoodbye() {
        String goodbye = "     Bye. Hope to see you again soon!";

        printMessage(goodbye);

        return goodbye;
    }
}
