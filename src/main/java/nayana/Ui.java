package nayana;
import java.util.ArrayList;
import java.util.Scanner;
import nayana.task.*;
import nayana.command.*;

/**
 * Handles interactions with the user in the console.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the Ui class with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }


    private static final String  WELCOME_MESSAGE=
          "\n" +
          " ___ .-.     .---.   ___  ___    .---.   ___ .-.     .---.\n" +
          "(   )   \\   / .-, \\ (   )(   )  / .-, \\ (   )   \\   / .-,\\\n" +
          " |  .-. .  (__) ; |  | |  | |  (__) ; |  |  .-. .  (__) ; |\n" +
          " | |  | |    .'  |   | |  | |  .'  |  |  | |  | |  .'  |  |\n" +
          " | |  | |   / .'| |  | '  | |  / .'|  |  | |  | |   / .'| |\n" +
          " | |  | |  | /  | |  '  -' |  | /  |  |  | |  | |  | /  | |\n" +
          " | |  | |  ; |  ; |   .__. |  ; |  ;  |  | |  | |  ; |  ; |\n" +
          " | |  | |  ' -'  |   ___ | |  ' -'    |  | |  | |  ' -'  |\n" +
          "(___)(___) .__.'_.  (   )' |  .__.'_.   (___)(___) .__.'_.\n" +
          "                      ; -' '\n" +
          "                       .__.'";
    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        System.out.println("OOPS!!! There was an error loading the tasks. Starting with an empty list.");
    }

    /**
     * Displays the welcome message and application introduction.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello from" + WELCOME_MESSAGE);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Nayana");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line for separation.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void printAddTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks left in the list.%n", size);
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public void printDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks left in the list.%n", size);
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints the entire task list.
     *
     * @param tasks The TaskList object containing the tasks.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    /**
     * Prints a message indicating that the application is exiting.
     */
    public void printExit() {
        System.out.println("Bye!!! Hope to help you again soon!");
        scanner.close();
    }

    /**
     * Prints the list of tasks that match the search criteria.
     *
     * @param foundTasks An ArrayList of tasks that match the search query.
     */
    public void printFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + "." + foundTasks.get(i));
            }
        } else {
            System.out.println("There are no matching tasks in your list.");
        }
    }
}