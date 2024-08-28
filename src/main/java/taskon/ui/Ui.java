package taskon.ui;

import static taskon.common.Messages.MESSAGE_EMPTY_FIND;
import static taskon.common.Messages.MESSAGE_EXIT;
import static taskon.common.Messages.MESSAGE_GREETING;
import static taskon.common.Messages.MESSAGE_MARK;
import static taskon.common.Messages.MESSAGE_NO_TASKS;
import static taskon.common.Messages.MESSAGE_UNMARK;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import taskon.task.Task;
import taskon.task.TaskList;

/**
 * The Ui class handles all user interactions and outputs in the application.
 * It is responsible for reading user inputs and displaying messages to the user.
 */
public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "_________________________________________";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a Ui object with default input and output streams.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a Ui object with specified input and output streams.
     *
     * @param in The input stream to read user input.
     * @param out The output stream to print messages.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is only whitespace, or is empty.
     *
     * @param input full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean toIgnore(String input) {
        return input.trim().isEmpty();
    }

    /**
     * Reads a line of user input, ignoring any empty or whitespace-only input.
     *
     * @return The user input line as a String.
     */
    public String readCommand() {
        String inputString = in.nextLine();
        while (toIgnore(inputString)) {
            inputString = in.nextLine();
        }

        return inputString;
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        out.println(MESSAGE_GREETING);
    }

    /**
     * Displays an exit message to the user.
     */
    public void exit() {
        out.println(MESSAGE_EXIT);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public void listItems(TaskList tasks) {
        out.println("Here's what we've got on your to-do list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.getTask(i);
            System.out.println(i + 1 + "." + t.toString());
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void mark(Task task) {
        out.println(MESSAGE_MARK + task + LS);
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param task The task that has been unmarked.
     */
    public void unmark(Task task) {
        out.println(MESSAGE_UNMARK + task + LS);
    }

    /**
     * Displays tasks that occur on a specified date.
     *
     * @param date The date to search for tasks.
     * @param tasks The list of tasks to search through.
     */
    public void showTasksOnDate(String date, TaskList tasks) {
        LocalDate localDate = LocalDate.parse(date);
        boolean isFound = false;
        out.println("Tasks on " + localDate + " :");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.occursOn(localDate)) {
                out.println(task);
                isFound = true;
            }
        }
        if (!isFound) {
            out.println(MESSAGE_NO_TASKS);
        }
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that has been deleted.
     * @param size The updated size of the task list.
     */
    public void showDeleted(Task task, int size) {
        out.println("Alright, I've removed this task:\n    " + task + "\nNow you have " + size + " tasks.\n");
    }

    /**
     * Displays a message indicating that a new task has been added.
     *
     * @param task The task that has been added.
     * @param size The updated size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        out.println("Got it! I've added this task:\n    " + task + "\nNow you have "
                + size + " tasks in your list.\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        out.println(message);
    }

    /**
     * Displays a divider line to separate sections in the output.
     */
    public void showLine() {
        out.println(DIVIDER);
    }

    /**
     * Displays a message indicating that no tasks were found for the search criteria.
     */
    public void showEmptyFind() {
        out.println(MESSAGE_EMPTY_FIND);
    }

}
