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
    private static final String INTRO = "Hello! I'm " + NAME + " :D\n";

    /** The prompt message asking what the user wants to do. */
    private static final String QUESTION = "What can I do for you?\n";

    /** The farewell message displayed when the application exits. */
    private static final String GOODBYE = "Bye. Hope to see you again soon!\n";

    /** The {@code Scanner} object used to read user input from the console. */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new {@code TextUI} object.
     */
    public TextUI() {
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The next line of user input as a {@code String}.
     */
    public String nextLine() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the start message when the application begins.
     */
    public String printStart() {
        String start = INTRO + QUESTION;
        System.out.println(start);
        return start;
    }

    /**
     * Prints the list of tasks stored in the application.
     *
     * @param items The {@link TaskList} containing the tasks to be printed.
     */
    public String printTasks(TaskList items) {
        StringBuilder taskListToString = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            String printString = (i + 1) + ". " + items.get(i).printTask();
            System.out.println(printString);
            taskListToString.append(printString).append("\n");
        }
        return taskListToString.toString();
    }

    /**
     * Prints the number of tasks currently stored in the application.
     *
     * @param items The {@link TaskList} whose size is to be printed.
     */
    public String printTaskCount(TaskList items) {
        String printString = "Now you have " + items.size()
                + " tasks in the list";
        System.out.println(printString);
        return printString;
    }

    /**
     * Prints a {@link CitadelException} message to the console.
     *
     * @param e The {@link CitadelException} to be printed.
     */
    public String printCitadelException(CitadelException e) {
        System.out.println(e.toString());
        return e.toString();
    }

    /**
     * Prints an error message for date parsing errors.
     */
    public String printDateTimeParseException() {
        String dateErrorString = "Incorrect Date Format! Please write the date "
                + "in this format: dd/MM/yyyy HH:mm!";
        System.out.println(dateErrorString);
        return dateErrorString;
    }

    /**
     * Prints a generic exception message to the console.
     *
     * @param e The {@link Exception} to be printed.
     */
    public String printException(Exception e) {
        String exceptionString = "Error occurred: "
                + e.getMessage();
        System.out.println(exceptionString);
        return exceptionString;
    }

    /**
     * Prints the farewell message when the application exits.
     */
    public String printGoodbye() {
        System.out.println(GOODBYE);
        return GOODBYE;
    }

    /**
     * Prints a confirmation message after adding a task to the task list.
     *
     * @param t The {@link Task} that was added.
     * @param items The {@link TaskList} to which the task was added.
     */
    public static String printTask(Task t, TaskList items) {
        String addedSuccess = "Got it! I have added: " + t;
        String printTaskCount = "Now you have " + items.size()
                + " tasks in the list";
        System.out.println(addedSuccess);
        System.out.println(printTaskCount);
        return addedSuccess + "\n" + printTaskCount;
    }

    /**
     * Prints a confirmation message after marking a task as done.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     * @param index The index of the task that was marked as done.
     */
    public static String printMark(TaskList tasks, int index) {
        String markSuccess = "Nice! I've marked this task as done:";
        Task markedTask = tasks.get(index - 1);
        System.out.println(markSuccess);
        System.out.println(markedTask);
        return markSuccess + "\n" + markedTask;
    }

    /**
     * Prints a confirmation message after unmarking a task as not done.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     * @param index The index of the task that was unmarked.
     */
    public static String printUnmark(TaskList tasks, int index) {
        String unmarkSuccess = "Nice! I've marked this task as done:";
        Task unmarkedTask = tasks.get(index - 1);
        System.out.println(unmarkSuccess);
        System.out.println(unmarkedTask);
        return unmarkSuccess + "\n" + unmarkedTask;
    }

    /**
     * Prints a confirmation message after deleting a task from the task list.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     * @param t The {@link Task} that was deleted.
     */
    public static String printDelete(TaskList tasks, Task t) {
        String removeSuccess = "Noted. I've removed this task:";
        String printTaskCount = "Now you have " + tasks.size()
                + " tasks in the list";
        System.out.println(removeSuccess);
        System.out.println(t);
        System.out.println(printTaskCount);
        return removeSuccess + "\n" + t + "\n" + printTaskCount;
    }

    /**
     * Prints the tasks that match a given keyword search.
     *
     * @param tasks The {@link TaskList} containing the matching tasks.
     */
    public static String printFind(TaskList tasks) {
        String findMatches = " Here are the matching tasks in your list:";
        System.out.println(findMatches);
        new TextUI().printTasks(tasks);
        return findMatches + new TextUI().printTasks(tasks);
    }
}
