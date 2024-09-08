package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Handles interactions with the user, including displaying messages and reading user input.
 */
public class Ui {
    private Scanner scanner;
    private String lastResponse; // Field to store the last response

    /**
     * Constructs a Ui object that handles user input and output.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        lastResponse = ""; // Initialize last response
    }

    /**
     * Reads the next line of user input as a command.
     *
     * @return The user input command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        lastResponse = " Hello! I'm Duke\n What can I do for you?";
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        lastResponse = " Bye. Hope to see you again soon!";
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        lastResponse = message;
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list after adding.
     */
    public void showTaskAdded(Task task, int size) {
        lastResponse = " Got it. I've added this task:\n   " + task + "\n Now you have " + size + " tasks in the list.";
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks in the list after removal.
     */
    public void showTaskRemoved(Task task, int size) {
        lastResponse = " Noted. I've removed this task:\n   " + task + "\n Now you have " + size + " tasks in the list.";
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        lastResponse = " Nice! I've marked this task as done:\n   " + task;
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        lastResponse = " OK, I've marked this task as not done yet:\n   " + task;
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a list of tasks that match the given keyword.
     *
     * @param tasks The list of matching tasks.
     */
    public void showMatchingTasks(List<Task> tasks) {
        StringBuilder response = new StringBuilder(" Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        lastResponse = response.toString();
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays all tasks in the current task list.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTasks(List<Task> tasks) {
        StringBuilder response = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        lastResponse = response.toString();
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays tasks that are scheduled on a specific date.
     *
     * @param tasks The list of tasks occurring on the specified date.
     * @param date The date for which tasks are displayed.
     */
    public void showTasksOnDate(List<Task> tasks, LocalDate date) {
        StringBuilder response = new StringBuilder(" Here are the tasks occurring on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n");
        if (tasks.isEmpty()) {
            response.append(" No tasks found on this date.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                response.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
        }
        lastResponse = response.toString();
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been edited successfully.
     *
     * @param task The task that was edited.
     */
    public void showEditSuccess(Task task) {
        lastResponse = "Task edited successfully:\n   " + task;
        System.out.println("____________________________________________________________");
        System.out.println(lastResponse);
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns the last response displayed to the user.
     *
     * @return The last response string.
     */
    public String getLastResponse() {
        return lastResponse;
    }
}