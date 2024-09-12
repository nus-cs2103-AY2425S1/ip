package wolfie.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import wolfie.task.Task;

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
     * @return the error message
     */
    public String showLoadingError() {
        return "OOPS!!! Error loading tasks."; // Print the error message
    }

    /**
     * Prints the error message
     *
     * @param message the error message
     * @return the error message
     */
    public String showError(String message) {
        return " OOPS!!! " + message; // Print the specific error message
    }

    /**
     * Prints the goodbye message
     * @return the goodbye message
     */
    public String showGoodbye() {
        return """
                GOODBYE MY POOKIE BEAR! I'll be here if you need me.
                The program will exit in 5 seconds.
                """; // Print the goodbye message
    }
    /**
     * Prints the tasks that just got added to the list
     *
     * @param task the task that was added
     * @param size the new size of the task list
     * @return the task that was added
     */
    public String showTaskAdded(Task task, int size) {
        return " Yasss. I've added this task:\n   " + task + "\n Now you have " + size + " tasks in the list pookie!";
    }

    /**
     * Prints the tasks that just got removed from the list
     * @param task the task that was removed
     * @param size the new size of the task list
     * @return the task that was removed
     */
    public String showTaskRemoved(Task task, int size) {
        return " Roger. I've removed this task:\n   " + task + "\n Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints the tasks that just got marked as done
     * @param task the task that was marked as done
     * @return the task that was marked as done
     */
    public String showTaskMarked(Task task) {
        return " Nice! I've marked this task as done:\n   " + task + "\n" + " Good job my king!";
    }

    /**
     * Prints the tasks that just got marked as not done
     *
     * @param task the task that was marked as not done
     * @return the task that was marked as not done
     */
    public String showTaskUnmarked(Task task) {
        return " Nice! I've marked this task as not done yet:\n   " + task;
    }
    /**
     * Prints the tasks on the date specified
     *
     * @param date the date in format yyyy-MM-dd
     * @param tasks the list of tasks on the date
     * @return the tasks on the date
     */
    public String showTasksOnDate(LocalDate date, Task... tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks on ").append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                .append(":\n");
        if (tasks.length == 0) {
            sb.append("   There are no tasks on this date.\n");
        } else {
            for (Task task : tasks) {
                sb.append("   ").append(task).append("\n");
            }
        }
        return sb.toString();
    }
    /**
     * Prints the tasks that match the keyword
     *
     * @param matchingTasks the list of tasks that match the keyword
     * @return the tasks that match the keyword
     */
    public String showMatchingTasks(List<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
        }
        return sb.toString();
    }
    /**
     * Prints the tasks in the list
     *
     * @param taskListString the list of tasks in string format
     * @return the tasks in the list
     */
    public String showTaskList(String taskListString) {
        return "Here are the tasks in your list:\n" + taskListString;
    }
}
