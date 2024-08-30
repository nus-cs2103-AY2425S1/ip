package shrimp.utility;

import shrimp.task.Task;
import shrimp.task.TaskList;

import java.util.Scanner;

/**
 * The {@code Ui} class handles interactions with the user.
 * It provides methods to display various messages and prompt the user for input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} instance.
     * Initializes the scanner to read user input from the console.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the next line of user input.
     *
     * @return The next line of user input.
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Prints the application logo to the console.
     * The logo is displayed in cyan color.
     */
    public void printLogo() {
        String logo = """
                
                       .__          .__               \s
                  _____|  |_________|__| _____ ______ \s
                 /  ___/  |  \\_  __ \\  |/     \\\\____ \\\s
                 \\___ \\|   Y  \\  | \\/  |  Y Y  \\  |_> >
                /____  >___|  /__|  |__|__|_|  /   __/\s
                     \\/     \\/               \\/|__|   \s
                                                      \s
                """;
        System.out.println(AnsiCode.CYAN + logo);
    }

    /**
     * Prints a welcome message to the console.
     * The message is displayed in cyan color.
     */
    public void printWelcome() {
        String greetings = "Domo! Same desu~ I am shrimp, and I am happy to assist you! Hewwo? <3";
        System.out.println(AnsiCode.CYAN + greetings);
    }

    /**
     * Prints an exit message to the console.
     * The message signals the end of the session.
     */
    public void printExit() {
        String exit = "Byebye~ It's time to say goodbye for the day~ Hope you enjoyed and had fuuun~ " +
                "I'll see you later~";
        System.out.println(exit);
    }

    /**
     * Prints the list of tasks in the {@code TaskList} to the console.
     * Each task is displayed with a purple index number.
     *
     * @param taskList The {@code TaskList} containing the tasks to be displayed.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println("Gotchaaa~ Here's the list so far:");
        for (int i = 0; i < taskList.getCount(); i++) {
            Task task = taskList.getTask(i);
            String output = String.format("    %s.%s", i + 1, task);
            System.out.println(AnsiCode.PURPLE + output + AnsiCode.CYAN);
        }
        printTaskCount(taskList);
    }

    /**
     * Prints the current count of tasks in the {@code TaskList}.
     *
     * @param taskList The {@code TaskList} containing the tasks to be counted.
     */
    private void printTaskCount(TaskList taskList) {
        System.out.printf("Lemme count~ You now have %s item(s) in your list!%n", taskList.getCount());
    }

    /**
     * Prints a message indicating that a task has been marked as complete.
     *
     * @param task The {@code Task} that was marked as complete.
     */
    public void printMark(Task task) {
        String output = "heya~ I've checked this task as complete! Feels good, right?";
        System.out.println(output);
        System.out.println("    " + task);
    }

    /**
     * Prints a message indicating that a task has been unmarked (marked as incomplete).
     *
     * @param task The {@code Task} that was unmarked.
     */
    public void printUnmark(Task task) {
        String output = "Whoops~ I've unchecked the task as incomplete! Be careful next time~";
        System.out.println(output);
        System.out.println("    " + task);
    }

    /**
     * Prints a message indicating that a task has been deleted.
     * Also prints the current count of tasks in the {@code TaskList}.
     *
     * @param task The {@code Task} that was deleted.
     * @param taskList The {@code TaskList} after the task was deleted.
     */
    public void printDelete(Task task, TaskList taskList) {
        String output = "Done! The task has been deleted!";
        System.out.println(output);
        System.out.println("    " + task);
        printTaskCount(taskList);
    }

    /**
     * Prints a message indicating that a task has been added.
     * Also prints the current count of tasks in the {@code TaskList}.
     *
     * @param task The {@code Task} that was added.
     * @param taskList The {@code TaskList} after the task was added.
     */
    public void printAdd(Task task, TaskList taskList) {
        String output = "rawr! '" + task + "' has been added to the list~";
        System.out.println(output);
        printTaskCount(taskList);
    }

    /**
     * Prints an error message to the console.
     * The message is displayed in red color.
     *
     * @param message The error message to be displayed.
     */
    public void printError(String message) {
        System.out.println(AnsiCode.RED + "Oh nyoo~ " + message);
    }
}
