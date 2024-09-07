package citadel.ui;

import java.util.Scanner;

import citadel.Task.Task;
import citadel.Task.TaskList;
import citadel.exception.CitadelException;

/**
 * The {@code TextUI} class is responsible for managing all user interactions in the Citadel application.
 * It provides methods to display messages, handle input, and print task-related information to the console.
 */
public class TextUI {

    /** The name of the Citadel application. */
    private static final String NAME = "citadel";

    /** The introductory message displayed when the application starts. */
    private static final String INTRO = String.format("Hello! I'm %s :D%n", NAME);

    /** The prompt message asking what the user wants to do. */
    private static final String QUESTION = "What can I do for you?\n";

    /** The farewell message displayed when the application exits. */
    private static final String GOODBYE = "Bye. Hope to see you again soon!\n";

    /** The {@code Scanner} object used to read user input from the console. */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads the next line of input from the user.
     *
     * @return The next line of user input as a {@code String}.
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Displays the start message.
     */
    public String printStart() {
        return printMessage(INTRO + QUESTION);
    }

    /**
     * Prints tasks stored in the task list.
     *
     * @param items The {@link TaskList} containing tasks.
     * @return A string representation of tasks.
     */
    public String printTasks(TaskList items) {
        assert items != null : "Task list cannot be null";

        StringBuilder tasksToString = new StringBuilder();
        // Generate and accumulate all of the task strings together into a string
        for (int i = 0; i < items.size(); i++) {
            String printString = String.format("%d. %s%n", i + 1, items.get(i).printTask());
            assert printString != null : "Task string representation cannot be null";
            tasksToString.append(printString);
        }
        return printMessage(tasksToString.toString());
    }

    /**
     * Prints a {@link CitadelException} message.
     *
     * @param e The {@link CitadelException}.
     */
    public String printCitadelException(CitadelException e) {
        return printMessage(e.toString());
    }

    /**
     * Prints an error message for incorrect date format.
     */
    public String printDateTimeParseException() {
        String dateErrorString = "Incorrect Date Format! Please write the date in this format: dd/MM/yyyy HH:mm!";
        return printMessage(dateErrorString);
    }

    /**
     * Prints a generic exception message.
     *
     * @param e The {@link Exception}.
     */
    public String printException(Exception e) {
        String exceptionMessage = String.format("Error occurred: %s", e.getMessage());
        return printMessage(exceptionMessage);
    }

    /**
     * Prints the farewell message.
     */
    public String printGoodbye() {
        return printMessage(GOODBYE);
    }

    /**
     * Prints a message confirming task addition.
     *
     * @param t     The {@link Task} that was added.
     * @param items The {@link TaskList} containing the tasks.
     */
    public static String printTask(Task t, TaskList items) {
        assert t != null : "Task to be printed cannot be null";
        assert items != null : "Task list cannot be null";

        String addedSuccess = "Got it! I have added: " + t;
        String printTaskCount = "Now you have " + items.size()
                + " tasks in the list";
        System.out.println(addedSuccess);
        System.out.println(printTaskCount);
        return addedSuccess + "\n" + printTaskCount;
    }

    /**
     * Prints a message confirming task completion.
     *
     * @param tasks The {@link TaskList}.
     * @param index The index of the task to mark.
     */
    public static String printMark(TaskList tasks, int index) {
        return printTaskStatus("Nice! I've marked this task as done:", tasks, index);
    }

    /**
     * Prints a message confirming task unmarking.
     *
     * @param tasks The {@link TaskList}.
     * @param index The index of the task to unmark.
     */
    public static String printUnmark(TaskList tasks, int index) {
        return printTaskStatus("Nice! I've marked this task as not done:", tasks, index);
    }

    /**
     * Prints a message confirming task deletion.
     *
     * @param tasks The {@link TaskList}.
     * @param t     The {@link Task} that was deleted.
     */
    public static String printDelete(TaskList tasks, Task t) {
        String removeSuccess = String.format("Noted. I've removed this task: %s", t);
        String taskCount = String.format("Now you have %d tasks in the list", tasks.size());
        return printMessage(removeSuccess + "\n" + taskCount);
    }

    /**
     * Prints tasks that match a given search keyword.
     *
     * @param tasks The {@link TaskList}.
     */
    public static String printFind(TaskList tasks) {
        String findMatches = "Here are the matching tasks in your list:";
        return printMessage(findMatches + new TextUI().printTasks(tasks));
    }

    // Helper method to handle printing messages consistently
    public static String printMessage(String message) {
        System.out.println(message);
        return message;
    }

    // Helper method for printing task status (marking and unmarking)
    private static String printTaskStatus(String statusMessage, TaskList tasks, int index) {
        Task task = tasks.get(index - 1);
        return printMessage(statusMessage + "\n" + task);
    }
}
