package FRIDAY;

import java.util.Scanner;

/**
 * Handles the user interface operations for interacting with the user.
 * <p>
 * This class is responsible for displaying messages, prompting for user input,
 * and showing the status of tasks. It manages communication with the user
 * through console output and input.
 * </p>
 */
public class Ui {
    private static final String GREETING_MESSAGE = "Hello! I'm FRIDAY\nWhat can I do for you?\n";
    private static final String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String DIVIDER = "----------------------------------------\n";
    private Scanner scanner;

    /**
     * Constructs a new UI object and initializes the scanner for
     * reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greeting() {
        System.out.println(DIVIDER + GREETING_MESSAGE + DIVIDER);
    }

    /**
     * Displays a farewell message to the user.
     */
    public void farewell() {
        System.out.println(DIVIDER + FAREWELL_MESSAGE + DIVIDER);
    }

    /**
     * Displays a message indicating that a task has been added.
     * @param task the task that has been added
     * @param numTasks the updated number of tasks in the list
     */
    public void printAdd(Task task, int numTasks) {
        System.out.println(DIVIDER + "Got it. I've added this task:\n" + task.getDescription() + "\nNow you have " + numTasks + " tasks in your list\n" + DIVIDER);
    }

    /**
     * Displays a message indicating that a task has been removed.
     * @param task the task that has been removed
     * @param numTasks the updated number of tasks in the list
     */
    public void printRemove(Task task, int numTasks) {
        System.out.println(DIVIDER + "Noted. I've removed this task:\n" + task.getDescription() + "\nNow you have " + numTasks + " tasks in your list\n" + DIVIDER);
    }

    /**
     * Displays a message indicating that a task has been marked as complete.
     */
    public void printCheck() {
        System.out.println(DIVIDER + "Noted. I've marked this task as complete\n" + DIVIDER);
    }

    /**
     * Displays a message indicating that a task has been marked as incomplete.
     */
    public void printUncheck() {
        System.out.println(DIVIDER + "Noted. I've marked this task as incomplete\n" + DIVIDER);
    }

    /**
     * Reads user input from the console.
     * @return the user input as a string
     */
    public String readUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Checks if there is more input available from the user.
     * @return true if there is more input available, false otherwise
     */
    public boolean isActive() {
        return this.scanner.hasNextLine();
    }

    /**
     * Displays a string to the console.
     * @param input the string to be displayed
     */
    public void display(String input) {
        System.out.println(input);
    }
}
