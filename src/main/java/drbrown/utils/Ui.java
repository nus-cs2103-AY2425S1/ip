package drbrown.utils;

import drbrown.task.Task;

import java.util.Scanner;

/**
 * Handles the user interface for the DrBrown application.
 * Provides methods to interact with the user by displaying messages and reading input.
 */
public class Ui {

    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a Ui object.
     */
    public Ui() {}

    /**
     * Displays the greeting message to the user when the application starts.
     */
    public void showGreeting() {
        System.out.println("\nRoads? Where We're Going, We Don't Need Roads?! So, what can I help you with today?");
    }

    /**
     * Displays a line separator for visual clarity in the console output.
     */
    public void showLine() {
        System.out.println("——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
    }

    /**
     * Displays the end message to the user when the application is closing.
     */
    public void showEnd() {
        System.out.println("Your future is whatever you make it, so make it a good one! Until next time, keep the DeLorean ready!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message confirming the creation of a new task.
     *
     * @param task The task that was created.
     */
    public void showTaskCreation(Task task) {
        System.out.println(task.toUIString());
        System.out.println(task);
    }

    /**
     * Displays a message confirming the marking of a task as completed.
     *
     * @param markTask The task that was marked as completed.
     */
    public void showMarkTask(Task markTask) {
        System.out.println("Task complete! If my calculations are correct, when this baby hits 88 miles per hour... you're gonna see some serious progress!\n");
        System.out.println(markTask.toString());
    }

    /**
     * Displays a message confirming the unmarking of a task as incomplete.
     *
     * @param unmarkTask The task that was unmarked as incomplete.
     */
    public void showUnmarkTask(Task unmarkTask) {
        System.out.println("Looks like we're going back to the future—task unmarked! Time to revisit this one.\n");
        System.out.println(unmarkTask.toString());
    }

    /**
     * Displays the list of tasks to the user.
     */
    public void showList() {
        System.out.println("Here’s your list! Like Doc Brown’s flux capacitor, this will help keep everything in perfect sync!\n");
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param deleteTask The task that was deleted.
     */
    public void showDeleteTask(Task deleteTask) {
        System.out.println("That task's history has been erased! Just like Marty’s fading photo—it's gone, like it never existed!\n");
        System.out.println(deleteTask.toString());
    }

    /**
     * Displays the total count of tasks in the list.
     *
     * @param tasks The TaskList containing the current tasks.
     */
    public void showCount(TaskList tasks) {
        System.out.println("\nYour total count is now " + tasks.getCount() + "! Like the time circuits, everything's in sync – keep those tasks ticking along!\"");
    }

    /**
     * Displays a default message when the input is unrecognized.
     */
    public void showDefault() {
        System.out.println("I’m from the future, and even I don’t know what that means.");
    }

    /**
     * Reads a command input from the user.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void closeCommand() {
        scanner.close();
    }
}
