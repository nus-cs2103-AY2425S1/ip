package nuffle.ui;

import nuffle.exception.NuffleException;
import nuffle.task.Task;
import java.util.ArrayList;

/**
 * The Ui class handles the interaction with the user.
 * It displays messages to the user based on the commands executed.
 */
public class Ui {

    // Constructor
    public Ui() {
        System.out.println("Ui object created.");
    }

    /**
     * Prints a line separator to the console.
     */
    private String printLine() {
        return "---------------------------------------------";
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public String printWelcomeMessage() {
        // Greeting the users
        return printLine() +
                "Nuffle > Good day! I'm Nuffle.\n" +
                "Nuffle > What can I do for you today?\n" +
                printLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String markTaskMessage(Task task) {
        return printLine() +
                "Nice! I have marked this task as done!\n" +
                " " + task + "\n" +
                printLine();
    }

    /**
     * Displays an error message if there was an issue with marking a task.
     */
    public String markTaskError() {
        return printLine() +
                "Opps! There appears to be an index error!\n" +
                printLine();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public String unmarkTaskMessage(Task task) {
        return printLine() +
                "OK! I have marked this task as not done yet.\n" +
                " " + task + "\n" +
                printLine();
    }

    /**
     * Displays an error message if there was an issue with unmarking a task.
     */
    public String unmarkTaskError() {
        return printLine() +
                "Opps! There appears to be an index error!\n" +
                printLine();
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param listSize The current size of the task list.
     */
    public String addTaskMessage(Task task, int listSize) {
        return printLine() +
                " Got it. I've added this task:\n" +
                "   " + task + "\n" +
                " Now you have " + listSize + " tasks in the list.\n" +
                printLine();
    }

    /**
     * Displays a message indicating that a task has been removed from the list.
     *
     * @param task The task that was removed.
     * @param listSize The current size of the task list after removal.
     */
    public String deleteTaskMessage(Task task, int listSize) {
        return printLine() +
                "Noted. I've removed this task:\n" +
                "   " + task + "\n" +
                "Now you have " + listSize + " tasks in the list.\n" +
                printLine();
    }

    /**
     * Displays an error message if there was an issue with deleting a task.
     */
    public String deleteTaskError() {
        return printLine() +
                "Hmmm... The index provided seems to be out of range. Please try again.\n" +
                printLine();
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public String byeMessage() {
        return printLine() +
                "Nuffle > Bye. Hope to see you again soon!\n" +
                printLine();
    }

    /**
     * Displays an error message when a NuffleException is caught.
     *
     * @param e The exception that was caught.
     */
    public String exceptionErrorMessage(NuffleException e) {
        return printLine() +
                "Nuffle caught an error > " + e.getMessage() + "\n" +
                printLine();
    }

    public String displayFoundTasks(ArrayList<Task> inputList) {
        StringBuilder message = new StringBuilder();
        if (inputList.isEmpty()) {
            message.append("Opps! Seems like there are no matching tasks.\n");
        } else {
            message.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < inputList.size(); i++) {
                message.append((i + 1)).append(". ").append(inputList.get(i)).append("\n");
            }
        }
        return message.toString();
    }

}
