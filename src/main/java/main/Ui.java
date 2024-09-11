package main;

import task.Task;

import java.util.Scanner;

/**
 * Handles user interactions for the application.
 */
public class Ui {
    private Scanner scanner;
    public static String showMissingMarkIndex = "OOPS! Please provide the index of the task to mark as done.";
    public static String showNumberFormatExceptionMessage = "OOPS! The provided index is not a valid number.";

    /**
     * Initializes a new Ui object with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private static final String LOGO = " .----------------.  .----------------.  .----------------.  .----------------.\n" +
            "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
            "| |  ________    | || |      __      | || |    _______   | || |  ____  ____  | |\n" +
            "| | |_   ___ `.  | || |     /  \\     | || |   /  ___  |  | || | |_   ||   _| | |\n" +
            "| |   | |   `. \\ | || |    / /\\ \\    | || |  |  (__ \\_|  | || |   | |__| |   | |\n" +
            "| |   | |    | | | || |   / ____ \\   | || |   '.___`-.   | || |   |  __  |   | |\n" +
            "| |  _| |___.' / | || | _/ /    \\ \\_ | || |  |`\\____) |  | || |  _| |  | |_  | |\n" +
            "| | |________.'  | || ||____|  |____|| || |  |_______.'  | || | |____||____| | |\n" +
            "| |              | || |              | || |              | || |              | |\n" +
            "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------'";
    private static final String HORIZONTAL_LINE = "________________________________________";
    private static final String GOODBYE = "   ___                _ _                  _ \n" +
            "  / _ \\___   ___   __| | |__  _   _  ___  / \\\n" +
            " / /_\\/ _ \\ / _ \\ / _` | '_ \\| | | |/ _ \\/  /\n" +
            "/ /_\\\\ (_) | (_) | (_| | |_) | |_| |  __/\\_/ \n" +
            "\\____/\\___/ \\___/ \\__,_|_.__/ \\__, |\\___\\/   \n" +
            "                              |___/          ";

    /**
     * Displays the greeting message and logo to the user.
     */
    public static String displayGreeting() {
        return "Hello! I'm Dash! What can I do for you?";
    }

    /**
     * Displays the goodbye message to the user.
     */
    public static String displayGoodbye() {
        return "GOODBYE!";
    }

    /**
     * Prints a horizontal line for visual separation in the UI.
     */
    public static void insertLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public static String showError(String message) {
        return "ERROR: " + message;
    }

    /**
     * Shows a date-time parse error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public static String showDateTimeParseError(String message) {
        return "Date format is wrong, please input dd-mm-yyyy hhmm: " + message;
    }

    /**
     * Shows an unexpected error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public static String showUnexpectedError(String message) {
        return "UNEXPECTED ERROR: " + message;
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public static String showTaskList(TaskList tasks) {
        int length = tasks.size();
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Task t = tasks.get(i);
            int num = i + 1;
            message.append(num).append(".").append(t).append("\n");
        }
        return message.toString();
    }

    /**
     * Prints a message confirming that a deadline task has been added.
     *
     * @param tasks The list of tasks after the addition.
     */
    public static String showAddDeadline(TaskList tasks) {
        int num = tasks.size();
        return "Got it. I've added this task:\n" + tasks.get(num - 1) + "\n"
        + "Now you have " + num + " tasks in the list.";
    }
    public static String showDeleteTask(Task task, int num) {
        return "Noted. I've removed this task.\n" + task + "\n"
                + "Now you have " + (num - 1) + " tasks in the list.";
    };

    /**
     * Prints a message confirming that an event task has been added.
     *
     * @param tasks The list of tasks after the addition.
     */
    public static String showAddEvent(TaskList tasks) {
        int num = tasks.size();
        return "Got it. I've added this task:\n" + tasks.get(num - 1) + "\n"
                + "Now you have " + num + " tasks in the list.";
    }

    /**
     * Displays tasks that match the specified keyword.
     *
     * @param tasks   The list of tasks to search through.
     * @param keyword The keyword to search for in task descriptions.
     */
    public static String showKeywordTasks(TaskList tasks, String keyword) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list: \n");
        boolean notFound = true;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String[] words = t.getDescription().split(" ");
            for (String word : words) {
                if (word.equals(keyword)) {
                    message.append(t).append("\n");
                    notFound = false;
                    break;
                }
            }
        }
        if (notFound) {
            message.append("Oops! Keyword not found!");
        }
        return message.toString();
    }

    public static String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    public static String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays a loading error message.
     *
     * @param message The error message to be displayed.
     */
    public String showLoadingError(String message) {

        return "LOADING ERROR: " + message;
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The next line of user input.
     */
    public String nextInput() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a message confirming that a todo task has been added.
     *
     * @param tasks The list of tasks after the addition.
     */
    public static String showAddTodo(TaskList tasks) {
        int num = tasks.size();
        return "Got it. I've added this task:\n" + tasks.get(num - 1) + "\n"
                + "Now you have " + num + " tasks in the list.";
    }


}
