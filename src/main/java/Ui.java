import java.util.Scanner;

/**
 * Represents the user interface for fishman bot.
 * This class handles all the inputs and outputs operations.
 */
public class Ui {
    /** Scanner object to read user input. */
    private final Scanner scanner;

    /** Constructs a new Ui object and initializes the Scanner for user input. */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads inputs of the user.
     *
     * @return The user's input as String.
     */
    public String readCommands() {
        return scanner.nextLine();
    }

    /**
     * Displays the logo
     */
    public void displayLogo() {
        String logo =
                """
                          _____ _     _                          \s
                         |  ___(_)___| |__  _ __ ___   __ _ _ __ \s
                         | |_  | / __| '_ \\| '_ ` _ \\ / _` | '_ \\\s
                         |  _| | \\__ \\ | | | | | | | | (_| | | | |
                         |_|   |_|___/_| |_|_| |_| |_|\\__,_|_| |_|
                        """;
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Displays the welcome message.
     */
    public void displayWelcome() {
        System.out.println("Hello! I'm Fishman");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the goodbye message.
     */
    public void displayGoodbye() {
        System.out.println("Bloop bloop. Hope to see you again soon!");
    }

    /**
     * Displays the confirmation message after task addition.
     *
     * @param task The Task object that is added.
     * @param size The size of the task list.
     */
    public void displayAddedTask(Task task, int size) {
        System.out.println("Bloop bloop, I have added the following task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList object containing all tasks.
     */
    public void displayTaskList(TaskList tasks) {
        System.out.println("Bloop bloop, here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

    /**
     * Display the confirmation message after the task is marked or unmarked.
     *
     * @param task The task object that is marked or unmarked.
     */
    public void displayTaskStatus(Task task) {
        if (task.isDone) {
            System.out.println("Bloop bloop, I've marked this task as done:");
        } else {
            System.out.println("Bloop bloop, I've marked this task as not done yet: ");
        }
        System.out.println(task);
    }

    /**
     * Displays the confirmation message after task deletion.
     * @param task The task that is deleted.
     * @param size The size of the task list.
     */
    public void displayDeletedTask(Task task, int size) {
        System.out.println("Bloop bloop, I have removed the following task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Display the header to the error message.
     *
     * @param message The message header for the error.
     */
    public void displayError(String message) {
        System.out.println("Glub glub! " + message);
    }

}
