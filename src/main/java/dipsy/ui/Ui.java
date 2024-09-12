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
    private static final String INTRODUCTION = "Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam "
            + "and Purrtector of the Realm of Naps.\n"
            + "How can I purrvide assistance? Purrhaps I could lend a paw!";
    private static final String FAREWELL = "Fur-well friend, stay paw-sitive!"


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
     * Generates a welcome message to be displayed when the application starts.
     * @return A welcome message to be shown when at the start of the application.
     */
    public String getWelcomeMessage() {
        return INTRODUCTION;
    }

    /**
     * Generates a message to be displayed when the user exits the application.
     * @return A farewell message to be shown when the user exits the application.
     */
    public String getExitMessage() {
        return FAREWELL;
    }

    /**
     * Generates an error message with the specified content.
     *
     * <p>This method accepts one or more messages and concatenates them into a single error message.
     * If multiple messages are provided, they are appended with a space between them.</p>
     *
     * @param messages One or more error messages to be included in the error message.
     * @return A concatenated error message prefixed with "Error:", followed by the specified messages.
     */
    public String getErrorMessage(String... messages) {
        StringBuilder result = new StringBuilder("Error: ");
        for (String message : messages) {
            result.append(message).append(" ");
        }
        return result.toString().trim();
    }


    /**
     * Generates a message indicating that a task has been successfully added to the task list.
     * @param task          The task that was added.
     * @param numberOfTasks The total number of tasks in the list.
     * @return A message confirming that the task has been added, along with the task details
     *         and the total number of tasks in the list.
     */
    public String getTaskAddedMessage(Task task, int numberOfTasks) {
        assert task != null : "Task should not be null";
        assert numberOfTasks >= 0 : "Number of tasks should not be negative";

        return "(=ↀωↀ=)ノ Task added!\n" + task + "\nYou now have " + numberOfTasks + " tasks in your list.";
    }

    /**
     * Generates a message indicating that a task has been successfully deleted from the task list.
     *
     * @param task          The task that was deleted.
     * @param numberOfTasks The total number of remaining tasks in the list.
     * @return A message confirming the task deletion, including the task details
     *         and the total number of tasks in the list.
     */
    public String getTaskDeletedMessage(Task task, int numberOfTasks) {
        assert task != null : "Task should not be null";
        assert numberOfTasks >= 0 : "Number of tasks should not be negative";

        return "Purrr, I've swatted this task away:\n" + task
                + "\nYou now have " + numberOfTasks + " tasks in your list.";
    }

    /**
     * Generates a message indicating that a task has been successfully marked as done.
     *
     * @param task The task that was marked as done.
     * @return A message confirming that the task has been marked as done, along with the task details.
     */
    public String getMarkTaskDoneMessage(Task task) {
        assert task != null : "Task should not be null";

        return "Meow! I’ve scratched this task off the list!\n" + task;
    }

    /**
     * Generates a message indicating that a task has been successfully marked as undone.
     *
     * @param task The task that was marked as undone.
     * @return A message confirming that the task has been marked as undone, along with the task details.
     */
    public String getMarkTaskUndoneMessage(Task task) {
        assert task != null : "Task should not be null";

        return "Mrrreow! I’ve batted this task back onto the list.\n" + task;
    }

    /**
     * Generates a message with the list of tasks.
     * <p>If there are no tasks, a message indicating an empty list is generated.</p>
     *
     * @param tasks The list of tasks to print.
     * @return A message showing the list of tasks, or a message specifying that there are
     *         no tasks.
     */
    public String getTasksMessage(ArrayList<Task> tasks) {
        assert tasks != null : "ArrayList<Task> should not be null";

        int taskCount = tasks.size();
        if (taskCount == 0) {
            return "Your task list is as empty as a well-sunned nap spot.";
        }
        return formatTasksMessage("Time to stretch those paws and tackle your tasks!\n", tasks);
    }

    /**
     * Generates a message with the list of tasks that match a given keyword.
     * <p>If no tasks match, a message is printed.</p>
     *
     * @param tasks The list of tasks matching the keyword.
     * @return A message showing the list of tasks matching the keyword, or a message specifying
     *         that there are no tasks matching the keyword.
     */
    public String getTasksMatchingKeywordMessage(ArrayList<Task> tasks) {
        assert tasks != null : "ArrayList<Task> should not be null";

        return tasks.isEmpty()
                ? "There are no tasks in your list that match the keyword."
                : formatTasksMessage("Here is the list of matching tasks:\n", tasks);
    }

    /**
     * Formats a list of tasks with a given header and returns the resulting message.
     *
     * <p>This helper method generates a message that starts with a provided header, followed by the list of tasks,
     * each task being printed with 1-based indexing (i.e., the first task is labeled as 1, not 0).</p>
     *
     * @param header The header message to print before listing tasks.
     * @param tasks  The list of tasks to be formatted.
     * @return A formatted string containing the header and the list of tasks with 1-based indexing.
     */
    private String formatTasksMessage(String header, ArrayList<Task> tasks) {
        assert header != null : "Message header should not be null";
        assert tasks != null : "ArrayList<Task> should not be null";

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
