package arsenbot.ui;

import arsenbot.task.Task;
import arsenbot.task.TaskList;

import java.util.List;
import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It reads user input, displays messages, and shows task-related feedback.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs an Ui object and initializes a Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm ArsenBot");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Displays a separator line for better readability in the output.
     */
    public void showLine() {
        System.out.println("____________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads the next user command from input.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the exit message when the application is about to close.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task the task that was added
     * @param size the total number of tasks in the list after adding the new task
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task the task that was deleted
     * @param size the total number of tasks remaining in the list after deletion
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task the task that was marked as not done
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Displays the list of tasks currently in the task list.
     *
     * @param tasks the TaskList containing all the tasks to be displayed
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void showFoundTasks(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.get((i)));
            }
        }
    }
}
