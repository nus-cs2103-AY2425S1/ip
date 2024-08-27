package ui;
import task.Task;
import task.TaskList;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Buddy");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message when loading tasks from file fails.
     */
    public void showLoadingError() {
        System.out.println("Failed to load tasks from file!");
    }

    /**
     * Displays an error message when saving tasks to file fails.
     */
    public void showSavingError() {
        System.out.println("Failed to save tasks to file!");
    }

    /**
     * Displays an error message when an invalid command is entered.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message when a task is marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message when a task is deleted.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a list of tasks.
     */
    public void showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks in your list!");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
    }

    /**
     * Reads the next command from the user.
     *
     * @return The next command from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}