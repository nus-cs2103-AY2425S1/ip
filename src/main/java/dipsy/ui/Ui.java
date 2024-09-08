package dipsy.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import dipsy.task.Task;

/**
 * The {@code Ui} class handles user interaction by reading input and displaying output.
 * It provides methods for printing various messages, including task updates and error messages.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a {@code Ui} object that reads from {@link System#in} and writes to {@link System#out}.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a {@code Ui} object with specified input and output streams.
     *
     * @param in  The input stream to read user input from.
     * @param out The output stream to write messages to.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prompts the user to enter a command and returns the trimmed input.
     *
     * @return The user's input command as a trimmed string.
     */
    public String getInput() {
        out.print("Enter command: ");
        return in.nextLine().trim();
    }

    /**
     * Returns a message wrapped between two terminal lines for readability.
     *
     * @param message The message to print.
     */
    private String printWithTerminalLines(String message) {
        String terminalLine = "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
        return terminalLine + message + terminalLine;
    }

    /**
     * Prints a welcome message when the application starts.
     */
    public String getWelcomeMessage() {
        String introduction = "Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam "
                + "and Purrtector of the Realm of Naps.\n"
                + "How can I purrvide assistance? Purrhaps I could lend a paw!";
        return introduction;
    }

    /**
     * Prints an exit message when the user decides to close the application.
     */
    public String getExitMessage() {
        return "Fur-well friend, stay paw-sitive!";
    }

    /**
     * Prints an error message with the specified content.
     *
     * @param message The error message to print.
     */
    public String getErrorMessage(String message) {
        return "Error: " + message;
    }

    /**
     * Prints a message indicating that a task has been added to the list.
     *
     * @param task          The task that was added.
     * @param numberOfTasks The total number of tasks in the list.
     */
    public String getTaskAddedMessage(Task task, int numberOfTasks) {
        return "(=ↀωↀ=)ノ Task added!\n" + task + "\nYou now have " + numberOfTasks + " tasks in your list.";
    }

    /**
     * Prints a message indicating that a task has been deleted from the list.
     *
     * @param task          The task that was deleted.
     * @param numberOfTasks The total number of remaining tasks in the list.
     */
    public String getTaskDeletedMessage(Task task, int numberOfTasks) {
        return "Purrr, I've swatted this task away:\n" + task
                + "\nYou now have " + numberOfTasks + " tasks in your list.";
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String getMarkTaskDoneMessage(Task task) {
        return "Meow! I’ve scratched this task off the list!\n" + task;
    }

    /**
     * Prints a message indicating that a task has been marked as undone.
     *
     * @param task The task that was marked as undone.
     */
    public String getMarkTaskUndoneMessage(Task task) {
        return "Mrrreow! I’ve batted this task back onto the list.\n" + task;
    }

    /**
     * Prints the list of tasks. If there are no tasks, a message indicating an empty list is printed.
     *
     * @param tasks The list of tasks to print.
     */
    public String getTasksMessage(ArrayList<Task> tasks) {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            return "Your task list is as empty as a well-sunned nap spot.";
        }
        return formatTasksMessage("Time to stretch those paws and tackle your tasks!\n", tasks);
    }

    /**
     * Prints the list of tasks that match a given keyword. If no tasks match, a message is printed.
     *
     * @param tasks The list of tasks matching the keyword.
     */
    public String getTasksMatchingKeywordMessage(ArrayList<Task> tasks) {
        return tasks.isEmpty()
                ? "There are no tasks in your list that match the keyword."
                : formatTasksMessage("Here is the list of matching tasks:\n", tasks);
    }

    /**
     * Helper method to print tasks with a header and task list. The tasks are printed with 1-based indexing.
     *
     * @param header The header message to print before listing tasks.
     * @param tasks  The list of tasks to print.
     */
    private String formatTasksMessage(String header, ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder(header + "\n");
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; i++) {
            int printedIndex = i + 1; // table is 0-indexed, but we print starting from 1
            result.append(printedIndex).append(".").append(tasks.get(i));
            if (i < taskCount - 1) { // Don't append a newline after the last task
                result.append("\n");
            }
        }
        return result.toString();
    }
}
