package nuffle.ui;

import nuffle.exception.NuffleException;
import nuffle.task.Task;
import java.util.ArrayList;

/**
 * The Ui class handles the interaction with the user.
 * It displays messages to the user based on the commands executed.
 */
public class Ui {

    /**
     * Prints a line separator to the console.
     */
    private static void printLine() {
        System.out.println("---------------------------------------------");
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public static void welcomeMessage() {
        // Greeting the users
        printLine();
        System.out.println("Nuffle > Good day! I'm Nuffle.");
        System.out.println("Nuffle > What can I do for you today?");
        printLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public static void markTaskMessage(Task task) {
        printLine();
        System.out.println("Nice! I have marked this task as done!");
        System.out.println(" " + task);
        printLine();
    }

    /**
     * Displays an error message if there was an issue with marking a task.
     */
    public static void markTaskError() {
        printLine();
        System.out.println("Opps! There appears to be an index error!");
        printLine();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public static void unmarkTaskMessage(Task task) {
        printLine();
        System.out.println("OK! I have marked this task as not done yet.");
        System.out.println(" " + task);
        printLine();
    }

    /**
     * Displays an error message if there was an issue with unmarking a task.
     */
    public static void unmarkTaskError() {
        printLine();
        System.out.println("Opps! There appears to be an index error!");
        printLine();
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param listSize The current size of the task list.
     */
    public static void addTaskMessage(Task task, int listSize) {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + listSize + " tasks in the list.");
        printLine();
    }

    /**
     * Displays a message indicating that a task has been removed from the list.
     *
     * @param task The task that was removed.
     * @param listSize The current size of the task list after removal.
     */
    public static void deleteTaskMessage(Task task, int listSize) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + listSize + " tasks in the list.");
        printLine();
    }

    /**
     * Displays an error message if there was an issue with deleting a task.
     */
    public static void deleteTaskError() {
        printLine();
        System.out.println("Hmmm... The index provided seems to be out of range. Please try again.");
        printLine();
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public static void byeMessage() {
        printLine();
        System.out.println("Nuffle > Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Displays an error message when a NuffleException is caught.
     *
     * @param e The exception that was caught.
     */
    public static void exceptionErrorMessage(NuffleException e) {
        printLine();
        System.out.println("Nuffle caught an error > " + e.getMessage());
        printLine();
    }

    public static void displayFoundTasks(ArrayList<Task> inputList) {
        if (inputList.isEmpty()) {
            System.out.println("Opps! Seems like there is no matching tasks.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < inputList.size(); i++) {
                System.out.println((i + 1) + ". " + inputList.get(i));
            }
        }
    }

}
