package FRIDAY;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User interface of the app
 */
public class Ui {
    private static final String DIVIDER = "----------------------------------------\n";

    /**
     * Prints out message upon adding of task to the task list
     * @param task Task object
     * @param numTasks  number of tasks in list
     */
    public String printAdd(Task task, int numTasks) {
        return DIVIDER + "Got it. I've added this taskL:\n"
                + task.getTaskDescription() + "\nNow you have " + numTasks + " tasks in your list\n" + DIVIDER;
    }

    /**
     * Prints out message on deletion of task from task list
     * @param task Task object
     * @param numTasks size of task list
     */
    public String printRemove(Task task, int numTasks) {
        return DIVIDER + "Noted. I've removed this taskL:\n"
                + task.getTaskDescription() + "\nNow you have " + numTasks + " tasks in your list\n" + DIVIDER;
    }

    public String printCheck() {
        return DIVIDER + "Noted. I've marked this task as complete\n" + DIVIDER;
    }

    public String printUncheck() {
        return DIVIDER + "Noted. I've marked this task as incomplete\n" + DIVIDER;
    }
    /**
     * method displays the list of tasks in a specific format
     * @param searchResults array list of tasks
     */
    public String displaySearchResults(ArrayList<Task> searchResults) {
        StringBuilder output = new StringBuilder(DIVIDER + "Here are the matching tasks from your list\n:");
        searchResults.forEach((task) -> {
            output.append(task + "\n");
        });
        output.append("\n" + DIVIDER);
        return output.toString();
    }
}
