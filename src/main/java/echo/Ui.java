package echo;
import java.util.List;
import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It is responsible for displaying messages and receiving input from the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

<<<<<<< HEAD
    public static void showClassFound(List<Task> tasks) {
        for(int i = 0; i < tasks.size(); i++) {
            int no = i + 1;
            System.out.println(no + " . " + tasks.get(i).toString());
        }
    }

=======
    /**
     * Reads and returns the user's command as a trimmed, lowercase string.
     *
     * @return The user's command.
     */
>>>>>>> branch-A-JavaDoc
    public String readCommand() {
        return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        String logo = "____________________________________________________________\n";
        System.out.println("Hello, I'm Echo\n" + logo);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye bye...");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * Displays a loading error message when tasks fail to load.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
        } else {
            int count = 1;
            for (Task task : tasks) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    /**
     * Displays a message when a task is added, along with the total number of tasks in the list.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is removed, along with the total number of tasks remaining in the list.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks remaining in the list.
     */
    public void showTaskRemoved(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }


}

