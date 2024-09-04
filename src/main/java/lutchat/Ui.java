package lutchat;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Ui class handles the user interface interactions for the application.
 * It provides methods to display messages and prompt the user for input.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a Ui object with a new Scanner instance.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        showLine();
        System.out.println("Hello! I'm lutchat.Lutchat!");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void exit() {
        System.out.println("Bye! Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays a line of underscores to separate different UI sections.
     */
    public void showLine() {
        System.out.println("______________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
        showLine();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the task(s) in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that has been added.
     * @param size The updated number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " task(s) in the list.");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param size The updated number of tasks in the list.
     */
    public void showTaskRemoved(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " task(s) in the list.");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        showLine();
    }

    /**
     * Displays a response message to the user.
     *
     * @param message The response message to display.
     */
    public void showResponse(String message) {
        System.out.println(message);
        showLine();
    }
}
