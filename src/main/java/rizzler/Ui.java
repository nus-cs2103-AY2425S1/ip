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
     *
     * @return String greeting to the user.
     */
    String showGreeting() {
        return "Hello! I'm the Rizzler.\n"
                + "What can I do for you today?\n";
    }

    /**
     * Outputs a goodbye to the user.
     *
     * @return String goodbye to the user.
     */
    String showGoodbye() {
        return "Bye! Rizz you later!\n";
    }

    /**
     * Outputs the completion message of the addition to the list.
     *
     * @param task Task added to list.
     * @param size New size of list.
     * @return String detailing the addition.
     */
    String showAdditionToList(Task task, int size) {
        return "Gotcha! I've added the new task for you:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Outputs the completion message of the deletion from the list.
     *
     * @param task Task deleted from list.
     * @param size New size of list.
     * @return String detailing the removal.
     */
    String showRemovalFromList(Task task, int size) {
        return "I have removed this task for you:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Outputs the contents of the tasklist or
     * that no tasks are in the list if the list is empty.
     *
     * @param tasks List of tasks to output.
     * @return String detailing the list.
     */
    String showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks here yet\n";
        } else {
            String output = "Here are the tasks in your list\n";
            for (int i = 0; i < tasks.size(); i++) {
                output += String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
            }
            return output;
        }
    }

    /**
     * Outputs the completion message of the task marked as done.
     *
     * @param task Task marked as done.
     * @return String detailing the marking.
     */
    String showMarking(Task task) {
        return "Hell yeah! You finished your task:\n"
                + task + "\n";
    }

    /**
     * Outputs the completion message of the task marked as not done.
     *
     * @param task Task marked as not done.
     * @return String detailing the unmarking.
     */
    String showUnmarking(Task task) {
        return "Womp womp. Better do it later:\n"
                + task + "\n";
    }

    /**
     * Outputs the tasks found to match a given keyword.
     * If no tasks are found, outputs that no tasks are found.
     * 
     * @param tasks ArrayList of tasks that match the given keyword.
     * @return String detailing found tasks.
     */
    String showFoundList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "I couldn't find any tasks matching your keyword\n";
        } else {
            String output = "I found these tasks matching your keyword\n";
            for (int i = 0; i < tasks.size(); i++) {
                output += String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
            }
            return output;
        }
    }

    /**
     * Outputs error message when loading up.
     *
     * @return String for loading error.
     */
    String showLoadingError() {
        return "Oops, something went wrong while loading.";
    }

    /**
     * Outputs error message from <code>RizzlerException</code>
     * during the runtime of Rizzler.
     *
     * @param message Error message from <code>RizzlerException</code>.
     * @return String of error message.
     */
    String showError(String message) {
        return message + "\n";
    }
}
