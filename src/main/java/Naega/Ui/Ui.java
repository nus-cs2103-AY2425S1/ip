package Naega.Ui;

import Naega.Task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {

    /**
     * Displays a welcome message to the user.
     * This method prints a greeting and prompts the user for input.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Naega");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a separator line to the console.
     * This method is used to improve the readability of console output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message when loading tasks fails.
     * This method informs the user that there was an issue loading tasks from a file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays an error message with a custom message.
     * This method prints a message to the user indicating an error occurred.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(" Oops! " + message);
    }

    /**
     * Reads a command from the user input.
     * This method uses a `Scanner` to read a line of text entered by the user.
     *
     * @return the line of text entered by the user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints a message indicating a task has been added.
     * This method shows the added task and the updated number of tasks in the list.
     *
     * @param task the task that was added
     * @param size the updated number of tasks in the list
     */
    public void printTaskAdded(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been deleted.
     * This method shows the deleted task and the updated number of remaining tasks.
     *
     * @param task the task that was removed
     * @param remainingTasks the updated number of tasks remaining in the list
     */
    public void showDeletedTask(Task task, int remainingTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + remainingTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the tasks that match a search query.
     * This method prints the list of tasks that match the search criteria or a message indicating no tasks were found.
     *
     * @param tasks the list of tasks that match the search criteria
     */
    public void showFoundTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no matching tasks in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}