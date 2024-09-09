package max.main;

import max.exception.MaxException;
import max.task.Task;

import java.util.ArrayList;
import java.util.List;


/**
 * The Ui class handles all user interaction, printing messages and task information
 * to the console. It provides methods for printing tasks, messages, and program-specific
 * greetings and farewells.
 */
public class Ui {
    private StringBuilder output;

    /**
     * Constructs a new Ui object and initializes the output StringBuilder.
     */
    public Ui() {
        output = new StringBuilder();
    }

    /**
     * Resets the output by clearing the current content of the StringBuilder.
     */
    public void resetOutput() {
        output.setLength(0);
    }

    /**
     * Retrieves the current content of the output.
     *
     * @return A string representing the current content of the output.
     */
    public String getOutput() {
        return output.toString();
    }

    /**
     * Prints a message indicating that a task has been added, along with the current number
     * of tasks in the list.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void printTaskTypeAdded(Task task, int size) {
        assert size >= 0 : "Task list size cannot be negative.";

        printLine();
        printToMax("\t Got it. I've added this task:");
        printToMax("\t   " + task.toString());
        printToMax("\t Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        printToMax("\t " + message);
        printLine();
    }

    /**
     * Prints a welcome message when the program starts.
     */
    public void printHello() {
        printLine();
        printMessage("Hello! I'm Max\n\t What can I do for you?");
    }

    /**
     * Prints a goodbye message when the program ends.
     */
    public void printBye() {
        printLine();
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a horizontal line to separate different sections of output.
     */
    public void printLine() {
        printToMax("\t_______________________________________");
    }

    /**
     * Prints the list of tasks in the current task list.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void list(ArrayList<Task> tasks) {
        assert tasks != null : "Task list cannot be null.";

        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            printToMax("\t " + count + "." + tasks.get(i).toString());
        }
        printLine();
    }

    /**
     * Prints the search results for tasks that match the given tag.
     * If no tasks are found, it prints a message indicating so.
     * If tasks are found, it prints each task along with its associated tags.
     *
     * @param tasks The list of tasks matching the tag search.
     */
    public void printTagSearchResults(List<Task> tasks) {
        printLine();
        if (tasks.isEmpty()) {
            printToMax("\t  No tasks found with the given tag.");
        } else {
            printToMax("\t  Here are the tasks found with the given tag.");
            for (Task task : tasks) {
                printTaskWithTags(task);
            }
        }
        printLine();
    }

    /**
     * Prints a task along with its associated tags.
     * Tags are displayed as hashtags.
     *
     * @param task The task to be printed with its tags.
     */
    public void printTaskWithTags(Task task) {
        printToMax("\t " + task.toString());
    }

    /**
     * Prints the list of tasks to the console.
     * <p>
     * Displays a different message based on whether the tasks are filtered or not.
     * If {@code isFilter} is {@code true}, the message will indicate that only matching tasks are shown.
     * If {@code isFilter} is {@code false}, the message will indicate that all tasks are displayed.
     * </p>
     *
     * @param isFilter A boolean indicating whether the tasks are filtered.
     *                 {@code true} if only matching tasks are displayed,
     *                 {@code false} if all tasks are displayed.
     */
    public void printList(boolean isFilter) {
        printLine();
        if (isFilter) {
            printToMax("\t Here are the matching tasks in your list:");
        } else {
            printToMax("\t Here are the tasks in your list:");
        }
    }

    /**
     * Prints a message indicating that a task has been deleted, along with the current number
     * of tasks remaining in the list.
     *
     * @param removedTask The task that was removed.
     * @param size The current number of tasks in the list.
     */
    public void printDeleteTask(Task removedTask, int size) {
        assert size >= 0 : "Task list size cannot be negative after deletion.";

        printLine();
        printToMax("\t Noted. I've removed this task:");
        printToMax("\t   " + removedTask.toString());
        printToMax("\t Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkDone(Task task) {
        assert task != null : "Task cannot be null when marked as done.";

        printLine();
        printToMax("\t Nice! I've marked this task as done:");
        printToMax("\t   " + task.toString());
        printLine();
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printMarkNotDone(Task task) {
        assert task != null : "Task cannot be null when marked as not done.";

        printLine();
        printToMax("\t OK, I've marked this task as not done yet:");
        printToMax("\t   " + task.toString());
        printLine();
    }

    /**
     * Appends the specified string to the output.
     *
     * @param str The string to be appended to the output.
     */
    public void printToMax(String str) {
        output.append(str).append("\n");
    }

    /**
     * Prints the help information to guide users on how to use the application.
     */
    public void printHelp() {
        printLine();
        printToMax("Welcome to Max Help Page!");
        printToMax("Here are some commands you can use:");
        printToMax("");

        printToMax("'todo <description>'");
        printToMax("    Adds a new todo item.");
        printToMax("");

        printToMax("'deadline <description> /by <date>'");
        printToMax("    Adds a new deadline item.");
        printToMax("");

        printToMax("'event <description> /from <start> /to <end>'");
        printToMax("    Adds a new event item.");
        printToMax("");

        printToMax("'mark <index>'");
        printToMax("    Marks the task at the given index as done.");
        printToMax("");

        printToMax("'unmark <index>'");
        printToMax("    Marks the task at the given index as not done.");
        printToMax("");

        printToMax("'delete <index>'");
        printToMax("    Deletes the task at the given index.");
        printToMax("");

        printToMax("'list'");
        printToMax("    Lists all tasks.");
        printToMax("");

        printToMax("'find <keyword>'");
        printToMax("    Finds tasks containing the specified keyword.");
        printToMax("");

        printToMax("'searchtag <tag>'");
        printToMax("    Finds tasks containing the specified tag.");
        printToMax("");

        printToMax("'tag <index> <tag>'");
        printToMax("    Adds a tag to the task at the given index.");
        printToMax("");

        printToMax("'untag <index> <tag>'");
        printToMax("    Removes a tag from the task at the given index.");
        printToMax("");

        printToMax("'help'");
        printToMax("    Shows this help page.");
        printToMax("");

        printLine();
    }
}
