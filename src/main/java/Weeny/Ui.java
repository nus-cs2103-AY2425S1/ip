package weeny;

import java.util.List;
import java.util.Scanner;

/**
 * Handles user interactions by displaying messages and reading input.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Weeny\nWhat can I do for you?");
        printLine();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The updated number of tasks.
     */
    public void printTaskAddedMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The updated number of tasks.
     */
    public void showTaskDeletedMessage(Task task, int size) {
        printLine();
        System.out.println("Spooof! The task magically disappeared:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkMessage(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        printLine();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public void showMarkMessage(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        printLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        printLine();
        System.out.println("Error: " + message);
        printLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you soon!");
        printLine();
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return The user input.
     */
    public String readUserInput() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    /**
     * Prints a line separator.
     */
    private void printLine() {
        System.out.println("______________________________________________");
    }
}
