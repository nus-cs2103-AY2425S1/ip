package dude;

import java.util.ArrayList;
import java.util.Scanner;

import dude.exception.DudeException;
import dude.task.Task;
import dude.task.TaskList;

/**
 * Represents the user interface for interacting with the user.
 * This class handles input from the user and displays messages.
 */
public class Ui {
    private static final String BOT_NAME = "Dude";
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a Ui and initializes the Scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the greeting message.
     */
    public void showGreet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Reads and returns the user's input as a string.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the list of tasks currently in the TaskList.
     *
     * @param taskList The TaskList with list of tasks to display.
     */
    public void showList(TaskList taskList) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");

        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 1; i <= taskList.getLength(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }

        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMark(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmark(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been added to the TaskList.
     *
     * @param task The task that has been added.
     * @param taskList The TaskList containing the newly added task.
     */
    public void showAdd(Task task, TaskList taskList) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been removed from the TaskList.
     *
     * @param task The task that has been removed.
     * @param taskList The TaskList after the task has been removed.
     */
    public void showDelete(Task task, TaskList taskList) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays the filtered list of tasks that match the description.
     *
     * @param filteredList The filtered list of tasks to display.
     */
    public void showFind(ArrayList<Task> filteredList) {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 1; i <= filteredList.size(); i++) {
            System.out.println(i + "." + filteredList.get(i - 1));
        }

        System.out.println(LINE);
    }

    /**
     * Displays an error message when a DudeException occurs.
     *
     * @param e The DudeException that occurred.
     */
    public void showError(DudeException e) {
        System.out.println(LINE);
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

    /**
     * Displays a goodbye message.
     */
    public void showBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Closes the Scanner used for user input.
     */
    public void closeScanner() {
        scanner.close();
    }
}
