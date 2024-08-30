package nixy.ui;

import java.util.Iterator;
import java.util.Scanner;

import nixy.exceptions.NixyException;
import nixy.task.Task;
import nixy.task.TaskList;

/**
 * Ui class is responsible for handling user interface.
 * It provides methods to read input from the user and display messages to the user.
 */
public class Ui {

    static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Read input from the user.
     */
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    public static void showWelcome() {
        PrintUtility.wrapPrintWithHorizontalLines("Hello! I'm Nixy", "What can I do for you?");
    }

    public static void showGoodbye() {
        PrintUtility.wrapPrintWithHorizontalLines("Bye. Hope to see you again soon!");
    }

    /**
     * Display the list of tasks to the user.
     * @param tasks The list of tasks to display.
     */
    public static void showList(TaskList tasks) {
        utilDisplayList(tasks, "Here are the tasks in your list:");
    }

    /**
     * Displays the list of tasks that find retrieved.
     *
     * @param tasks The list of found tasks.
     */
    public static void showMatchingList(TaskList tasks) {
        utilDisplayList(tasks, "Here are the matching tasks in your list:");
    }

    /**
     * Helper method to display a list of tasks with a message.
     * The list is displayed with a number prefix.
     *
     * @param tasks The list of tasks to display.
     * @param message The message to display before the list.
     */
    private static void utilDisplayList(TaskList tasks, String message) {
        PrintUtility.indentPrint(HORIZONTAL_LINE);
        PrintUtility.indentPrint(message);
        Iterator<Task> tasksIterator = tasks.getTasksIterator();
        for (int i = 0; tasksIterator.hasNext(); i++) {
            Task task = tasksIterator.next();
            PrintUtility.indentPrint(String.format("%d. %s", i + 1, task));
        }
        PrintUtility.indentPrint(HORIZONTAL_LINE);
    }

    /**
     * Show the message that the task has been marked as done.
     * @param taskString The string represenation of task that was marked as done.
     */
    public static void showMarkedAsDone(String taskString) {
        PrintUtility.wrapPrintWithHorizontalLines("Nice! I've marked this task as done:",
            "  " + taskString);
    }

    /**
     * Show the message that the task has been marked as not done.
     * @param taskString The string represenation of task that was marked as not done.
     */
    public static void showMarkedAsUndone(String taskString) {
        PrintUtility.wrapPrintWithHorizontalLines("OK, I've marked this task as not done yet:",
            "  " + taskString);
    }

    /**
     * Show the message that the task has been deleted.
     * @param taskString The string represenation of task that was deleted.
     * @param taskCount The number of tasks remaining in the list.
     */
    public static void showDeletedTask(String taskString, int taskCount) {
        PrintUtility.wrapPrintWithHorizontalLines("Noted. I've removed this task:",
            "  " + taskString, String.format("Now you have %d tasks in the list.", taskCount));
    }

    /**
     * Display the NixyException message to the user.
     * @param e The exception to display.
     */
    public static void showNixyException(NixyException e) {
        PrintUtility.wrapPrintWithHorizontalLines(e.getMessage());
    }

    /**
     * Show the message that the task has been added.
     * @param task The task that was added.
     * @param taskCount The number of tasks in the list.
     */
    public static void showAddedTask(Task task, int taskCount) {
        PrintUtility.wrapPrintWithHorizontalLines("Got it. I've added this task:",
            "  " + task, String.format("Now you have %d tasks in the list.", taskCount));
    }

    /**
     * Alert user that command is not recognized.
     */
    public static void showUnknownWarning() {
        PrintUtility.wrapPrintWithHorizontalLines("HEY YOU ARE TYPING WEIRD THINGS! I don't understand.");
    }


}
