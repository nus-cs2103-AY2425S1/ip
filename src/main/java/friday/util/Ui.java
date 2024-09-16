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
        String greeting = "     Hello Mr Stark, I'm Friday, your personal assistant.\n"
                + "     How may I assist you today?";

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
        assert message != null : "Error message should not be null";

        String error = "     Apologies, Mr Stark but there was an issue:\n"
                + "     " + message;

        printMessage(error);

        return error;
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
        String loadingError = "     Pardon me, Mr Stark. "
                + "I encountered an issue loading your tasks. Starting with a blank slate.";

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
        assert task != null : "Task should not be null";
        String message = "     Copied that, Mr Stark. I've successfully added the following task:\n"
                + "       " + task + "\n"
                + "     You now have " + noOfTasks + " tasks in your list.";

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
        assert task != null : "Task should not be null";
        String message = "     Roger that, Mr Stark. I've removed the following task:\n"
                + "       " + task + "\n"
                + "     You now have " + noOfTasks + " remaining tasks.";

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
        assert task != null : "Task should not be null";
        String message = "     Noted, Mr Stark. Task marked as completed:\n"
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
        assert task != null : "Task should not be null";

        String message = "     Noted, Mr Stark. Task marked as not completed:\n"
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
        assert tasks != null : "TaskList should not be null";

        StringBuilder output = new StringBuilder();
        if (tasks.isTaskListEmpty()) {
            output.append("     Mr Stark, unfortunately your task list is currently empty.");
        } else {
            output.append("     Noted, Mr Stark. Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("         ").append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
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
        assert tasks != null : "TaskList should not be null";
        assert date != null : "Date should not be null";

        StringBuilder output = new StringBuilder();
        if (tasks.isTaskListEmpty()) {
            output.append("     Mr Stark, unfortunately there is no tasks scheduled for this date.");
        } else {
            output.append("     Noted, Mr Stark. Here are your tasks for ")
                    .append(date.format(DateTimeFormatter.ofPattern(DISPLAY_FORMAT_PATTERN)))
                    .append(":\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("         ").append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
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
        assert tasks != null : "TaskList should not be null";

        StringBuilder output = new StringBuilder();
        if (tasks.isTaskListEmpty()) {
            output.append("     Mr Stark, unfortunately there is no tasks matched your search.");
        } else {
            output.append("     Noted, Mr Stark. Here are the tasks that match your query:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("         ").append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
            }
        }

        printMessage(output.toString());

        return output.toString();
    }

    /**
     * Displays the list of tasks sorted by date.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     * @return The list of tasks sorted by date as a String.
     */
    public String showSortedTasks(TaskList tasks) {
        assert tasks != null : "TaskList should not be null";

        StringBuilder output = new StringBuilder();
        if (tasks.isTaskListEmpty()) {
            output.append("     Your task list is currently empty.");
        } else {
            output.append("     Noted, Mr Stark. Here are your tasks sorted by date:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("         ").append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
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
        String goodbye = "     Goodbye, Mr Stark. I'll be here when you need me next.";

        printMessage(goodbye);

        return goodbye;
    }
}
