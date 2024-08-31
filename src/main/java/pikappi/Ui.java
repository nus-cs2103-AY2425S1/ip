package pikappi;

import java.util.ArrayList;
import java.util.Scanner;

import pikappi.task.Task;

/**
 * Represents a user interface that interacts with the user.
 */
public class Ui {
    protected static Scanner reader = new Scanner(System.in);

    /**
     * Returns a Ui object that interacts with the user.
     */
    public Ui() {
    }

    /**
     * Greets the user when the program starts.
     */
    public void greet() {
        System.out.println("Pika! I'm Pikappi!\nWhat can I do for you?\n");
    }

    /**
     * Bids farewell to the user when the program ends.
     */
    public void goodbye() {
        System.out.println("Pi-kapi! See you again~\n");
    }

    /**
     * Shows a horizontal line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the user input from the command line.
     *
     * @return User input from the command line
     */
    public String readCommand() {
        return reader.nextLine();
    }

    /**
     * Shows an error message to the user.
     *
     * @param message Error message to be shown to the user
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Shows the added task to the user.
     *
     * @param task Task that was added
     * @param taskCount Number of tasks in the list
     */
    public void showAddedTask(Task task, int taskCount) {
        System.out.println("Pi-ka-pipi! I've added this task:\n " + task.toString()
                + "\nNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows the deleted task to the user.
     *
     * @param task Task that was deleted
     * @param taskCount Number of tasks in the list
     */
    public void showDeletedTask(Task task, int taskCount) {
        System.out.println("Pipi-ka-pi! I've removed this task:\n " + task.toString()
                + "\nNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows the matching tasks to the user.
     *
     * @param matchingTasks TaskList list of tasks that contains matching tasks
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.getTasks().isEmpty()) {
            System.out.println("Pika..? No matching tasks found..");
        } else {
            System.out.println("Pika! Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.getTasks().size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.getTasks().get(i));
            }
        }
    }

    /** Shows the user that there is no task in the list. */
    public void showNoTasks() {
        System.out.println("Pika-ka! You have no tasks!");
    }

    /**
     * Shows all tasks in the list to the user.
     *
     * @param tasks TaskList list of tasks to be shown
     */
    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + "." + tasks.get(i));
            } else {
                break;
            }
        }
    }

    /**
     * Shows the task that was marked as done to the user.
     *
     * @param task Task that was marked as done
     */
    public void showMarkedTask(Task task) {
        System.out.println("Pika! I've marked this task as done:\n" + task);
    }

    /**
     * Shows the task that was unmarked as not done yet to the user.
     *
     * @param task Task that was unmarked as not done yet
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("kaPi! I've unmarked this task as not done yet:\n" + task);
    }
}
