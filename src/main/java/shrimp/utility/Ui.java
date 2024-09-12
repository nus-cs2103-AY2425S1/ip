package shrimp.utility;

import java.util.Scanner;

import shrimp.task.Task;
import shrimp.task.TaskList;

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
    }

    /**
     * Prints a welcome message to the console.
     * The message is displayed in cyan color.
     */
    public String printWelcome() {
        String greetings = "Domo! Same desu~ I am shrimp, and I am happy to assist you! Hewwo? <3";
        return greetings;
    }

    /**
     * Prints an exit message to the console.
     * The message signals the end of the session.
     */
    public String printExit() {
        String exit = "Byebye~ It's time to say goodbye for the day~ Hope you enjoyed and had fuuun~ "
                + "I'll see you later~";
        return exit;
    }

    /**
     * Prints the list of tasks in the {@code TaskList} to the console.
     * Each task is displayed with a purple index number.
     *
     * @param taskList The {@code TaskList} containing the tasks to be displayed.
     */
    public String printTaskList(TaskList taskList) {
        String output = "Gotchaaa~ Here's the list so far:";
        for (int i = 0; i < taskList.getCount(); i++) {
            Task task = taskList.getTask(i);
            output += String.format("\n    %s. %s", i + 1, task);
        }
        output = output + printTaskCount(taskList);
        return output;
    }

    /**
     * Prints the current count of tasks in the {@code TaskList}.
     *
     * @param taskList The {@code TaskList} containing the tasks to be counted.
     */
    private String printTaskCount(TaskList taskList) {
        return String.format("\nLemme count~ You now have %s item(s) in your list!%n", taskList.getCount());
    }

    /**
     * Prints a message indicating that a task has been marked as complete.
     *
     * @param task The {@code Task} that was marked as complete.
     */
    public String printMark(Task task) {
        String output = "heya~ I've checked this task as complete! Feels good, right?";
        output = output + "\n    " + task;
        return output;
    }

    /**
     * Prints a message indicating that a task has been unmarked (marked as incomplete).
     *
     * @param task The {@code Task} that was unmarked.
     */
    public String printUnmark(Task task) {
        String output = "Whoops~ I've unchecked the task as incomplete! Be careful next time~";
        output = output + "\n    " + task;
        return output;
    }

    /**
     * Prints a message indicating that a task has been deleted.
     * Also prints the current count of tasks in the {@code TaskList}.
     *
     * @param task The {@code Task} that was deleted.
     * @param taskList The {@code TaskList} after the task was deleted.
     */
    public String printDelete(Task task, TaskList taskList) {
        String output = "Done! The task has been deleted!";
        output = output + "\n    " + task;
        output += printTaskCount(taskList);
        return output;
    }

    /**
     * Prints a message indicating that a task has been added.
     * Also prints the current count of tasks in the {@code TaskList}.
     *
     * @param task The {@code Task} that was added.
     * @param taskList The {@code TaskList} after the task was added.
     */
    public String printAdd(Task task, TaskList taskList) {
        String output = "rawr! '" + task + "' has been added to the list~";
        output += printTaskCount(taskList);
        return output;
    }

    public String printFind(TaskList taskList) {
        String output = "Heya~ Here's all the tasks I found matches your description~";
        for (int i = 0; i < taskList.getCount(); i++) {
            Task task = taskList.getTask(i);
            output += String.format("\n    %s.%s", i + 1, task);
        }
        return output;
    }

    /**
     * Prints an error message to the console.
     * The message is displayed in red color.
     *
     * @param message The error message to be displayed.
     */
    public String printError(String message) {
        return "Oh nyoo~ " + message;
    }
}
