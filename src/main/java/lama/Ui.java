package lama;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 * Provides methods to display messages and take input from the user.
 */
public class Ui {

    private static final String BAR = "____________________________________________________________";

    private Scanner scanner;

    /**
     * Construct an Ui object.
     * Initialise the scanner to read the user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Read the command entered by user.
     *
     * @return The command by user as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Display a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(BAR);
        System.out.println("Hello! I'm lama.Lama");
        System.out.println("What can I do for you?");
        System.out.println(BAR + "\n");
    }

    /**
     * Display a message indicating that a task is being added to the task list.
     * Display the number of task in the task list.
     *
     * @param taskList Task list after the task being added.
     */
    public void showAddCommand(TaskList taskList) {
        System.out.println(BAR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(BAR + "\n");
    }

    /**
     * Display the header message when a task is deleted.
     */
    public void showDeleteCommandHeader() {
        System.out.println(BAR);
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Display the footer message after a task has been deleted.
     * Display the number of task remained.
     *
     * @param taskList Task list after the task being deleted.
     */
    public void showDeleteCommandFooter(TaskList taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(BAR + "\n");
    }

    /**
     * Display a message to user when user exit the application.
     */
    public void showExitCommand() {
        System.out.println(BAR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BAR);
    }

    /**
     * Display a message indicating which task being marked.
     *
     * @param taskList Task list containing marked task.
     * @param indexOfMark Integer index of the task that was marked.
     */
    public void showMarkCommand(TaskList taskList, int indexOfMark) {
        System.out.println(BAR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(indexOfMark));
        System.out.println(BAR + "\n");
    }

    /**
     * Display a message indicating which task being unmarked.
     *
     * @param taskList Task list containing unmarked task.
     * @param indexOfUnmark Integer index of the task that was unmarked.
     */
    public void showUnmarkCommand(TaskList taskList, int indexOfUnmark) {
        System.out.println(BAR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList.get(indexOfUnmark));
        System.out.println(BAR + "\n");
    }

    /**
     * Display the list of tasks to the user.
     *
     * @param taskList Task list that will be displayed.
     */
    public void showListCommand(TaskList taskList) {
        System.out.println(BAR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        System.out.println(BAR + "\n");
    }

    /**
     * Displays an error message indicating there was a problem loading the file.
     */
    public void showLoadingError() {
        System.out.println(BAR);
        System.out.println("Sorry, there's error loading the file!");
        System.out.println("Please Try Again!");
        System.out.println(BAR + "\n");
    }

    /**
     * Displays a custom error message to the user.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        System.out.println(BAR);
        System.out.println(error);
        System.out.println(BAR + "\n");
    }

}
