package zero.ui;

import java.util.Scanner;
import zero.exception.ZeroException;
import zero.task.Task;
import zero.task.TaskList;

/**
 * The {@code Ui} class is responsible for interacting with the user.
 * It handles input and output operations, including displaying messages and reading user commands.
 */
public class Ui {
    private Scanner scanner;
    private boolean isDone = false;

    /**
     * Constructs a {@code Ui} object and initialises the {@code Scanner} for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The command entered by the user as a {@code String}.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm zero.Zero");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the goodbye message to the user and sets the {@code isDone} flag to {@code true}.
     */
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Baibai!");
        System.out.println("____________________________________________________________");
        this.isDone = true;
    }


    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println(" oopsie, " + message);
        showLine();
    }

    /**
     * Displays a message indicating that a task has been added, along with the updated task count.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the task list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been deleted, along with the updated task count.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the task list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays a message indicating that a task has been unmarked (marked as not done).
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The task list containing the tasks to display.
     * @throws ZeroException If there is an error accessing the task list.
     */
    public void listTasks(TaskList tasks) throws ZeroException {
        showLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        showLine();
    }

    public boolean isDone() {
        return this.isDone;
    }
}
