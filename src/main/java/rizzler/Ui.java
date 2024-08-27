package rizzler;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Represents the UI of Rizzler, handling user input and printing
 * output to the user.
 */
class Ui {
    private static final String separator =
            "_______________________________________________________\n";

    /**
     * Outputs a greeting to the user.
     */
    void showGreeting() {
        System.out.println(separator
                + "Hello! I'm the Rizzler.\n"
                + "What can I do for you today?\n"
                + separator);
    }

    /**
     * Outputs a goodbye to the user.
     */
    void showGoodbye() {
        System.out.println(separator
                + "Bye! Rizz you later!\n"
                + separator);
    }

    /**
     * Takes in a command input from the user.
     *
     * @return String representing command input from user.
     */
    String readCommand() {
        Scanner sc = new Scanner(System.in);
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    /**
     * Outputs the completion message of the addition to the list.
     *
     * @param task Task added to list.
     * @param size New size of list.
     */
    void showAdditionToList(Task task, int size) {
        System.out.println(separator
                + "Gotcha! I've added the new task for you:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + separator);
    }

    /**
     * Outputs the completion message of the deletion from the list.
     *
     * @param task Task deleted from list.
     * @param size New size of list.
     */
    void showRemovalFromList(Task task, int size) {
        System.out.println(separator
                + "I have removed this task for you:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + separator);
    }

    /**
     * Outputs the contents of the tasklist or
     * that no tasks are in the list if the list is empty.
     *
     * @param tasks List of tasks to output.
     */
    void showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(separator
                    + "No tasks here yet\n"
                    + separator);
        } else {
            System.out.println(separator
                    + "Here are the tasks in your list");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.valueOf(i + 1) + ". " + tasks.get(i));
            }
            System.out.println(separator);
        }
    }

    /**
     * Outputs the completion message of the task marked as done.
     *
     * @param task Task marked as done.
     */
    void showMarking(Task task) {
        System.out.println(separator
                + "Hell yeah! You finished your task:\n"
                + task + "\n"
                + separator);
    }

    /**
     * Outputs the completion message of the task marked as not done.
     *
     * @param task Task marked as not done.
     */
    void showUnmarking(Task task) {
        System.out.println(separator
                + "Womp womp. Better do it later:\n"
                + task + "\n"
                + separator);
    }

    /**
     * Outputs error message when loading up.
     */
    void showLoadingError() {
        System.out.println(separator
                + "Oops, something went wrong while loading."
                + separator);
    }

    /**
     * Outputs error message from <code>RizzlerException</code>
     * during the runtime of Rizzler.
     *
     * @param message Error message from <code>RizzlerException</code>.
     */
    void showError(String message) {
        System.out.println(separator
                + message + "\n"
                + separator);
    }
}
