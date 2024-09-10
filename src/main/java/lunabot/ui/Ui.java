package lunabot.ui;

import java.util.ArrayList;
import java.util.Scanner;

import lunabot.task.Task;

/**
 * Handles user interface operations for LunaBot.
 * The Ui class is responsible for managing the user interface interactions for LunaBot,
 * including reading user input and displaying messages to the console. It provides methods
 * for printing various types of messages such as welcome messages, error messages, task lists,
 * and updates on task status.
 */
public class Ui {
    private static final String LINE = "___________________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs an Ui object and initializes the scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The line of input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void printWelcome() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm LunaBot");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void printGoodbye() {
        System.out.println(LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Displays an error message when tasks cannot be loaded from a file.
     */
    public void printLoadingError() {
        System.out.println(LINE);
        System.out.println(" Unable to load tasks from file.");
        System.out.println(LINE);
    }

    /**
     * Displays a generic error message with the provided message.
     *
     * @param message The error message to display.
     */
    public void printError(String message) {
        System.out.println(LINE);
        System.out.println(" Error: " + message);
        System.out.println(LINE);
    }

    /**
     * Displays the current list of tasks, if the taskList is empty, the user is informed.
     *
     * @param taskList The list of tasks to display.
     */
    public void printTaskList(ArrayList<Task> taskList) {
        System.out.println(LINE);
        if (taskList.isEmpty()) {
            System.out.println(" You have no tasks in your task list.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + "." + taskList.get(i));
            }
        }
        System.out.println(LINE);
    }

    /**
     * Informs the user that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void printTaskMarked(Task task) {
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    /**
     * Informs the user that a task has been marked as not done yet.
     *
     * @param task The task that has been marked as not done.
     */
    public void printTaskUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    /**
     * Informs the user that a task has been deleted and shows the updated number of tasks.
     *
     * @param task The task that has been deleted.
     * @param size The updated number of tasks in the list.
     */
    public void printTaskDeleted(Task task, int size) {
        System.out.println(LINE);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println(LINE);

    }

    /**
     * Informs the user that a task has been added and shows the updated number of tasks.
     *
     * @param task The task that has been added.
     * @param size The updated number of tasks in the list.
     */
    public void printTaskAdded(Task task, int size) {
        System.out.println(LINE);
        System.out.println(" Got it! I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list");
        System.out.println(LINE);
    }
}
