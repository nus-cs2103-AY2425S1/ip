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
     */
    public void displayAddedTask(Task task) {
        System.out.println("Bloop bloop, I have added the following task: ");
        System.out.println("Added: " + task);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList object containing all tasks.
     */
    public void displayTaskList(TaskList tasks) {
        System.out.println("Bloop bloop, here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

}
