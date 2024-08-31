package bobby.ui;

import bobby.exceptions.InvalidTaskNumberException;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * The {@code Ui} class handles all user interactions, including displaying messages
 * to the user and reading input from the console. It serves as the interface between
 * the user and the application.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} object with a {@code Scanner} for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        String greeting = "Hello I'm Bobby\n" + "What can I do for you today?";
        System.out.println(greeting);
    }

    /**
     * Displays an exit message when the user exits the application.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a command input from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message indicating that a task has been added successfully.
     *
     * @param task the task that was added
     * @param size the current number of tasks in the list
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Task added successfully:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Displays a message indicating that a task has been deleted successfully.
     *
     * @param task the task that was deleted
     * @param size the current number of tasks remaining in the list
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Task removed successfully:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done: " + task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task the task that was marked as not done
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet: " + task);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList the list of tasks to be displayed
     * @throws InvalidTaskNumberException if an invalid task number is accessed
     */
    public void showTasks(TaskList taskList) throws InvalidTaskNumberException {
        if (taskList.isEmpty()) {
            System.out.println("No tasks added to the list yet.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(String.format("%d.%s", i + 1, taskList.get(i)));
            }
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * Displays a message indicating the tasks found on a specific date.
     *
     * @param date the date for which tasks are being displayed
     */
    public void showFindTasksMessage(LocalDate date) {
        System.out.println("Tasks on " + date + ":");
    }

    /**
     * Displays a message indicating that no tasks were found for the given date.
     */
    public void showNoTasksFound() {
        System.out.println("No tasks found for the given date.");
    }
}
