package GPT;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Ui (User Interface) class handles all interactions with the user in the GPT application.
 * This class is responsible for displaying messages, reading user input, and providing feedback based on user commands.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object that uses a Scanner to read user input from the console.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user when the application starts.
     *
     * @param chatbotName The name of the chatbot to be displayed in the welcome message.
     */
    public void showWelcomeMessage(String chatbotName) {
        System.out.println("Type 'list' to display saved tasks or 'bye' to exit.");
        printLine();
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Displays a goodbye message to the user when the application exits.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a horizontal line to separate different sections of output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return The trimmed input string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Displays a message to the user when a task is added to the list.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks in the list after adding the new task.
     */
    public void showTaskAdded(Task task, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    /**
     * Displays a message to the user when a task is removed from the list.
     *
     * @param task      The task that was removed.
     * @param taskCount The total number of tasks remaining in the list after removing the task.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        printLine();
    }
    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param tasks The list of tasks that match the search keyword.
     */
    public void showMatchingTasks(List<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        showLine();
    }

    /**
     * Prints a horizontal line to separate different sections of output.
     */
    private void printLine() {
        System.out.println("____________________________________________________________");
    }
}
