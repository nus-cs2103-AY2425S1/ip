package sentinel.ui;

import java.util.Scanner;

import sentinel.Sentinel;
import sentinel.task.Task;
import sentinel.utils.SentinelList;
import sentinel.utils.SentinelString;

/**
 * Provides the user interface for the Sentinel application.
 * This class handles all interactions with the user, including displaying messages,
 * reading user input, and providing guidance on how to use the application's features.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructs a new Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message and application logo.
     */
    public void showWelcome() {
        System.out.println(SentinelString.stringWelcome());
    }

    /**
     * Prints a horizontal line for visual separation in the UI.
     */
    public void showLine() {
        System.out.println(SentinelString.stringLine());
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return The trimmed line of input from the user.
     */
    public String readLine() {
        return sc.nextLine().trim();
    }

    /**
     * Displays an error message when an exception is thrown.
     *
     * @param e The exception to display.
     */
    public void showError(Exception e) {
        System.out.println(SentinelString.stringError(e));
    }

    /**
     * Displays a goodbye message when the application ends.
     */
    public void showGoodbye() {
        System.out.println(SentinelString.stringGoodbye());
    }

    /**
     * Displays help information for the user, listing all available commands and their descriptions.
     */
    public void showHelp() {
        System.out.println(SentinelString.stringHelp());
    }

    /**
     * Displays guidelines for using the 'event' command.
     */
    public void showEventCommandGuidelines() {
        System.out.println(SentinelString.stringEventCommandGuidelines());
    }

    /**
     * Displays an error message when the start date is after the end date for an event.
     */
    public void showEventDateOrder() {
        System.out.println(SentinelString.stringEventDateOrder());
    }

    /**
     * Displays guidelines for using the 'deadline' command.
     */
    public void showDeadlineCommandGuidelines() {
        System.out.println(SentinelString.stringDeadlineCommandGuidelines());
    }

    /**
     * Displays guidelines for modifying tasks (marking, unmarking, deleting).
     */
    public void showModifyGuidelines() {
        System.out.println(SentinelString.stringModifyGuidelines());
    }

    /**
     * Displays an error message when an invalid index is provided.
     */
    public void showNoItemExists() {
        System.out.println(SentinelString.stringNoItemExists());
    }

    /**
     * Displays an error message when the task name is empty.
     *
     * @param commandType The type of command that requires a task name.
     */
    public void showEmptyTaskNameError(Sentinel.CommandType commandType) {
        System.out.println(SentinelString.stringEmptyTaskNameError(commandType));
    }

    /**
     * Displays an error message for unrecognized commands.
     */
    public void showUnrecognisedCommand() {
        System.out.println(SentinelString.stringUnrecognisedCommand());
    }

    /**
     * Displays the status and details of a task after it has been marked or unmarked.
     *
     * @param t The task that was marked or unmarked.
     */
    public void showTaskMark(Task t) {
        System.out.println(SentinelString.stringTaskMark(t));
    }

    /**
     * Displays the list of tasks.
     *
     * @param lst The list of tasks to display.
     */
    public void showList(SentinelList lst) {
        System.out.println(SentinelString.stringList(lst));
    }

    /**
     * Displays the filtered list of tasks.
     *
     * @param lst The list of tasks to filter from.
     */
    public void showFilteredList(SentinelList lst, String keyword) {
        System.out.println(SentinelString.stringFilteredList(lst, keyword));
    }

    /**
     * Displays a message when a task is removed and shows the number of remaining tasks.
     *
     * @param list   The updated list of tasks.
     * @param removed The task that was removed.
     */
    public void showRemovedAndRemaining(SentinelList list, Task removed) {
        System.out.println(SentinelString.stringRemovedAndRemaining(list, removed));
    }

    /**
     * Displays a message when a task is already marked as done or undone.
     *
     * @param t The task that is already marked.
     */
    public void showAlreadyMarked(Task t) {
        System.out.println(SentinelString.stringAlreadyMarked(t));
    }

    /**
     * Displays a message when a new task is added.
     *
     * @param t The task that was added.
     */
    public void showAddedTask(Task t) {
        System.out.println(SentinelString.stringAddedTask(t));
    }
}
