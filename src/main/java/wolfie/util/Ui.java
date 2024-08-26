package wolfie.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import wolfie.task.Task;
import wolfie.task.TaskList;

/**
 * Represents the user interface of the application
 */
@SuppressWarnings("checkstyle:Regexp")
public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message and art
     */
    public void showWelcome() { // Prints the art and welcome message
        String wolfieArt =
                """
                         __        __   _ _  __ _\s
                         \\ \\      / /__| | |/ _| |
                          \\ \\ /\\ / / _ \\ | | |_| |
                           \\ V  V /  __/ | |  _|_|
                            \\_/\\_/ \\___|_|_|_| (_)
                        """;
        System.out.println("____________________________________________________________");
        System.out.println(wolfieArt);
        System.out.println(" Hello Dean's Lister! I'm Wolfie");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the command from the user
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine(); // Read the command from the user on the console
    }

    /**
     * Prints the line divider
     */
    public void showLine() {
        System.out.println("____________________________________________________________"); // Print the line divider
    }

    /**
     * Prints the error message when loading tasks
     */
    public void showLoadingError() {
        System.out.println(" OOPS!!! Error loading tasks."); // Print the error message
    }

    /**
     * Prints the error message
     *
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println(" OOPS!!! " + message); // Print the specific error message
    }

    /**
     * Prints the goodbye message
     */
    public void showGoodbye() {
        System.out.println(" Bye Dean's Lister! Hope to see you again soon :-)"); // Print the goodbye message
    }

    /**
     * Prints the tasks in the list
     *
     * @param tasks the list of tasks
     */
    public void showTasks(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i)); // Print the task number and task
        }
    }
    /**
     * Prints the tasks that just got added to the list
     *
     * @param task the task that was added
     * @param size the new size of the task list
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println(" Got it! I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the tasks that just got removed from the list
     * @param task the task that was removed
     * @param size the new size of the task list
     */
    public void showTaskRemoved(Task task, int size) {
        System.out.println(" Noted! I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " remaining tasks in the list.");
    }

    /**
     * Prints the tasks that just got marked as done
     *
     * @param task the task that was marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    /**
     * Prints the tasks that just got marked as not done
     *
     * @param task the task that was marked as not done
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(" OK! I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    /**
     * Prints the tasks that are due on a specific date
     *
     * @param date the date to check for tasks
     * @param tasks the list of tasks due on that date
     */
    public void showTasksOnDate(LocalDate date, List<Task> tasks) {
        System.out.println(" Here are the tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
        for (Task task : tasks) {
            System.out.println("   " + task);
        }
    }

    public void showMessage(String s) {
        System.out.println(s);
    }
}
