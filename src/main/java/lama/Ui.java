package lama;

import java.util.Scanner;

import lama.task.Task;


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
        showBar();
        System.out.println("Hello! I'm lama.Lama");
        System.out.println("What can I do for you?");
        showBarFooter();
    }

    /**
     * Display a message indicating that a task is being added to the task list.
     * Display the number of task in the task list.
     *
     * @param taskList Task list after the task being added.
     */
    public void showAddCommand(TaskList taskList) {
        showBar();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        showBarFooter();
    }

    /**
     * Display the header message when a task is deleted.
     */
    public void showDeleteCommandHeader() {
        showBar();
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
        showBarFooter();
    }

    /**
     * Display a message to user when user exit the application.
     */
    public void showExitCommand() {
        showBar();
        System.out.println("Bye. Hope to see you again soon!");
        showBar();
    }

    /**
     * Display a message indicating which task being marked.
     *
     * @param taskList    Task list containing marked task.
     * @param indexOfMark Integer index of the task that was marked.
     */
    public void showMarkCommand(TaskList taskList, int indexOfMark) {
        showBar();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(indexOfMark));
        showBarFooter();
    }

    /**
     * Display a message indicating which task being unmarked.
     *
     * @param taskList      Task list containing unmarked task.
     * @param indexOfUnmark Integer index of the task that was unmarked.
     */
    public void showUnmarkCommand(TaskList taskList, int indexOfUnmark) {
        showBar();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList.get(indexOfUnmark));
        showBarFooter();
    }

    /**
     * Display the list of tasks to the user.
     *
     * @param taskList Task list that will be displayed.
     */
    public void showListCommand(TaskList taskList) {
        showBar();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        showBarFooter();
    }

    /**
     * Displays an error message indicating there was a problem loading the file.
     */
    public void showLoadingError() {
        showBar();
        System.out.println("Sorry, there's error loading the file!");
        System.out.println("Please Try Again!");
        showBarFooter();
    }

    /**
     * Displays a custom error message to the user.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        showBar();
        System.out.println(error);
        showBarFooter();
    }

    /**
     * Display the list of tasks that match with the word given.
     *
     * @param filteredList TaskList that contains the matched tasks.
     */
    public void showFindCommand(TaskList filteredList) {
        showBar();
        if (filteredList.size() == 0) {
            System.out.println("No matching tasks found!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < filteredList.size(); i++) {
                Task task = filteredList.get(i);
                System.out.println((i + 1) + "." + task);
            }
        }
        showBarFooter();
    }

    private void showBar() {
        System.out.println(BAR);
    }

    private void showBarFooter() {
        System.out.println(BAR + "\n");
    }

    private void showTaskList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
}
