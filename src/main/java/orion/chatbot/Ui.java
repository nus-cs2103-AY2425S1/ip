package orion.chatbot;

import orion.tasks.Task;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code Ui} class handles all interactions with the user. It provides
 * methods for printing messages to the console and reading user input.
 * The class is responsible for displaying various user interface elements
 * such as greetings, task lists, and error messages.
 */
public class Ui {

    /**
     * The logo of the Orion chatbot displayed during the greeting.
     */
    public static final String LOGO = "             .__               \n"
            + "  ___________|__| ____   ____  \n"
            + " /  _ \\_  __ \\  |/  _ \\ /    \\ \n"
            + "(  <_> )  | \\/  (  <_> )   |  \\\n"
            + " \\____/|__|  |__|\\____/|___|  /\n"
            + "                            \\/ \n";

    /**
     * A horizontal bar used to visually separate sections in the console output.
     */
    public static final String BAR = "______________________________________________\n";

    /**
     * An indentation string used to format printed messages with indentation.
     */
    public static final String INDENT = "    ";

    /**
     * The {@code Scanner} used to read input from the console.
     */
    private final Scanner sc;

    /**
     * Constructs a {@code Ui} object and initializes the {@code Scanner}.
     */
    protected Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a horizontal bar to the console.
     */
    protected void printBar() {
        System.out.println(Ui.BAR);
    }

    /**
     * Prints a message with indentation to the console.
     *
     * @param message The message to be printed.
     */
    private void printIndent(String message) {
        System.out.println(Ui.INDENT + message);
    }

    /**
     * Prints the greeting message and logo to the console.
     */
    public void printGreet() {
        printBar();
        System.out.println(Ui.LOGO);
        printBar();

        printIndent("Hello from Orion!");
        printIndent("We're fetching your task list from the cosmos...");
        printBar();
    }

    /**
     * Prints the goodbye message to the console.
     */
    public void printGoodbye() {
        printIndent("Bye! Hope to see you again soon!");
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command input by the user.
     */
    protected String readCommand() {
        String input = sc.nextLine();
        printBar();
        return input;
    }

    /**
     * Prints the list of tasks to the console.
     *
     * @param taskDescriptions A list of task descriptions to be printed.
     */
    public void printTaskList(List<String> taskDescriptions) {
        printIndent("Here are the tasks in your list:");
        for (String s : taskDescriptions) {
            printIndent(s);
        }
    }

    /**
     * Prints a message indicating that a task has been added to the task list.
     *
     * @param tasks The {@code TaskList} to which the task has been added.
     * @param task  The task that was added.
     */
    public void printAddTask(TaskList tasks, Task task) {
        printIndent("Sure! I've added the following task to your list:");
        printIndent(task.toString());
        printIndent("Now you have " + tasks.getNoTasks() + " tasks in your list.");
    }

    /**
     * Prints a message indicating that a task has been deleted from the task list.
     *
     * @param tasks The {@code TaskList} from which the task has been deleted.
     * @param task  The task that was deleted.
     */
    public void printDeleteTask(TaskList tasks, Task task) {
        printIndent("Sure! I've deleted the following task:");
        printIndent(task.toString());
        printIndent("Now you have " + tasks.getNoTasks() + " tasks in your list.");
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkTask(Task task) {
        printIndent("Sure! I've marked the following task as done:");
        printIndent(task.toString());
    }

    /**
     * Prints a message indicating that a task has been marked as undone.
     *
     * @param task The task that was marked as undone.
     */
    public void printUnmarkTask(Task task) {
        printIndent("Sure! I've marked the following task as undone:");
        printIndent(task.toString());
    }

    /**
     * Prints a message indicating the number of tasks loaded from storage.
     *
     * @param taskNo The number of tasks loaded.
     */
    protected void printLoadTasks(int taskNo) {
        printIndent(String.format("Welcome back! You have %d tasks in your task list.", taskNo));
    }

    /**
     * Prints an error message indicating that tasks could not be loaded.
     */
    protected void printLoadingError() {
        printIndent("Your existing task list is somewhere amongst the stars...");
        printIndent("We can't seem to find it!");
        printIndent("We've created a new task list for you.");
    }

    /**
     * Prints a message indicating that the task list has been saved successfully.
     */
    public void printSaveTasks() {
        printIndent("Your task list has been saved successfully!");
    }

    /**
     * Prints an error message indicating that the task list could not be saved.
     */
    public void printSaveError() {
        printIndent("We had a problem saving your task list... Sorry about that!");
    }

    /**
     * Prints an error message to the console.
     *
     * @param message The error message to be printed.
     */
    public void printErrorMessage(String message) {
        printIndent(message);
    }

    /**
     * Closes the {@code Scanner} used for reading user input.
     */
    public void closeScanner() {
        sc.close();
    }
}
