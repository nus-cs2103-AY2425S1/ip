package main;

import task.Task;

import java.util.Scanner;

/**
 * Handles user interactions for the application.
 */
public class Ui {
    private Scanner scanner;

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
    public static void displayGreeting() {
        System.out.println("Hello! I'm\n" + LOGO + "\nWhat can I do for you?");
        Ui.insertLine();
    }

    /**
     * Displays the goodbye message to the user.
     */
    public static void displayGoodbye() {
        Ui.insertLine();
        System.out.println(GOODBYE);
        Ui.insertLine();
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
    public static void showError(String message) {
        Ui.insertLine();
        System.out.println("ERROR: " + message);
        Ui.insertLine();
    }

    /**
     * Shows a date-time parse error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public static void showDateTimeParseError(String message) {
        Ui.insertLine();
        System.out.println("Date format is wrong, please input dd-mm-yyyy hhmm: " + message);
        Ui.insertLine();
    }

    /**
     * Shows an unexpected error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public static void showUnexpectedError(String message) {
        Ui.insertLine();
        System.out.println("UNEXPECTED ERROR: " + message);
        Ui.insertLine();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public static void showTaskList(TaskList tasks) {
        int length = tasks.size();
        Ui.insertLine();
        for (int i = 0; i < length; i++) {
            Task t = tasks.get(i);
            int num = i + 1;
            System.out.println(num + "." + t);
        }
        Ui.insertLine();
    }

    /**
     * Prints a message confirming that a deadline task has been added.
     *
     * @param tasks The list of tasks after the addition.
     */
    public static void printAddDeadline(TaskList tasks) {
        int num = tasks.size();
        Ui.insertLine();
        System.out.println("Got it. I've added this task:\n" + tasks.get(num - 1));
        System.out.println("Now you have " + num + " tasks in the list.");
        Ui.insertLine();
    }

    /**
     * Prints a message confirming that an event task has been added.
     *
     * @param tasks The list of tasks after the addition.
     */
    public static void printEvent(TaskList tasks) {
        int num = tasks.size();
        Ui.insertLine();
        System.out.println("Got it. I've added this task:\n" + tasks.get(num - 1));
        System.out.println("Now you have " + num + " tasks in the list.");
        Ui.insertLine();
    }

    /**
     * Displays tasks that match the specified keyword.
     *
     * @param tasks   The list of tasks to search through.
     * @param keyword The keyword to search for in task descriptions.
     */
    public static void showKeywordTasks(TaskList tasks, String keyword) {
        Ui.insertLine();
        System.out.println("Here are the matching tasks in your list: ");
        boolean notFound = true;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String[] words = t.getDescription().split(" ");
            for (String word : words) {
                if (word.equals(keyword)) {
                    System.out.println(t);
                    notFound = false;
                    break;
                }
            }
        }
        if (notFound) {
            System.out.println("Oops! Keyword not found!");
        }
        Ui.insertLine();
    }

    /**
     * Displays a loading error message.
     *
     * @param message The error message to be displayed.
     */
    public void showLoadingError(String message) {
        System.out.println("LOADING ERROR: " + message);
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
    public static void printAddTodo(TaskList tasks) {
        int num = tasks.size();
        Ui.insertLine();
        System.out.println("Got it. I've added this task:\n" + tasks.get(num - 1));
        System.out.println("Now you have " + num + " tasks in the list.");
        Ui.insertLine();
    }
}
