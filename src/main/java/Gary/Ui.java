package Gary;

import java.util.ArrayList;
import java.util.Scanner;

import Gary.task.Task;

/**
 * The {@code Ui} class is responsible for interacting with the user.
 * It handles user input and output, including displaying messages and tasks.
 */
public class Ui {
    // Scanner for reading user input
    private Scanner detector;

    /**
     * Constructs a {@code Ui} object with a new {@code Scanner} instance for input.
     */
    public Ui() {
        this.detector = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        System.out.println("Hello! I'm Gary\nWhat can I do for you?");
    }

    /**
     * Displays a goodbye message and closes the input scanner.
     */
    public void goodbye() {
        this.detector.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The {@code Task} object that has been marked as done.
     */
    public void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The {@code Task} object that has been marked as not done.
     */
    public void unmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The {@code Task} object that has been removed.
     * @param size The new size of the task list after the task has been removed.
     */
    public void deleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task from the list:\n"
                + task.toString() + "\nNow you have " + size
                + " tasks in the list.\n");
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The {@code Task} object that has been added.
     * @param size The new size of the task list after the task has been added.
     */
    public void addTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays all tasks in the provided {@code TaskList}.
     *
     * @param taskList The {@code TaskList} object containing tasks to display.
     */
    public void showTaskLists(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i));
        }
    }

    /**
     * Displays a list of matching tasks found by the find command.
     *
     * @param tasks The list of matching {@code Task} objects to display.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The string input entered by the user.
     */
    public String read() {
        return detector.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}

