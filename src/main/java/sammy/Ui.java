package sammy;

import sammy.task.Task;
import sammy.task.TaskList;

import java.util.Scanner;

/**
 * Handles interactions with the user by displaying messages and reading input.
 */
import java.util.Scanner;

public class Ui {

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String TASK_ADDED_MESSAGE = " Got it. I've added this task:\n";
    private static final String TASK_REMOVED_MESSAGE = " Noted. I've removed this task:\n";
    private static final String MARK_TASK_DONE_MESSAGE = " Nice! I've marked this task as done:\n";
    private static final String UNMARK_TASK_MESSAGE = " OK, I've marked this task as not done yet:\n";
    private static final String TASK_LIST_HEADER = " Here are the tasks in your list:\n";
    private static final String MATCHING_TASKS_HEADER = "Here are the matching tasks in your list:\n";

    /**
     * Displays a welcome message when the program starts.
     */
    public String showWelcomeMessage() {
        String welcomeMessage = buildMessage(" Hello! I'm sammy.ui.sammy.Sammy.", " What can I do for you?");
        printMessage(welcomeMessage);

        return welcomeMessage;
    }

    /**
     * Displays a goodbye message when the program ends.
     */
    public String showGoodbyeMessage() {
        String goodbyeMessage = buildMessage(" Bye. Hope to see you again soon!");
        printMessage(goodbyeMessage);
        return goodbyeMessage;
    }

    /**
     * Displays a horizontal line used as a separator for messages.
     */
    public String showLine() {
        return LINE_SEPARATOR;
    }

    /**
     * Displays an error message with the specified message content.
     *
     * @param message The error message to be displayed.
     */
    public String showErrorMessage(String message) {
        String errorMessage = buildMessage(" OOPS!!!", message);
        printMessage(errorMessage);
        return errorMessage;
    }

    /**
     * Displays all the tasks in the given TaskList.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        assert tasks != null : "TaskList cannot be null";
        StringBuilder taskList = new StringBuilder();
        taskList.append(LINE_SEPARATOR).append("\n").append(TASK_LIST_HEADER);

        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(" ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        taskList.append(LINE_SEPARATOR);
        return taskList.toString();
    }

    /**
     * Returns a string showing the task that has been tagged.
     *
     * @param task The task that has been tagged.
     * @return A string representation of the tagged task.
     */
    public String showTaggedTask(Task task) {
        return "Tagged Task: \n" + task.toString();
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that has been added.
     * @param size The current number of tasks in the list.
     */
    public String showAddTask(Task task, int size) {
        StringBuilder addTaskMessage = buildTaskActionMessage(TASK_ADDED_MESSAGE, task, size);
        return addTaskMessage.toString();
    }

    /**
     * Displays a message confirming that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param size The current number of tasks in the list.
     */
    public String showRemoveTask(Task task, int size) {
        StringBuilder removeTaskMessage = buildTaskActionMessage(TASK_REMOVED_MESSAGE, task, size);
        return removeTaskMessage.toString();
    }

    public String showMarkTask(Task task) {
        return buildTaskActionMessage(MARK_TASK_DONE_MESSAGE, task).toString();
    }

    public String showUnmarkTask(Task task) {
        return buildTaskActionMessage(UNMARK_TASK_MESSAGE, task).toString();
    }

    public String showFindResults(TaskList tasks) {
        assert tasks != null : "TaskList cannot be null";
        StringBuilder findResult = new StringBuilder();

        findResult.append(MATCHING_TASKS_HEADER);

        for (int i = 0; i < tasks.size(); i++) {
            findResult.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        return findResult.toString();
    }

    /**
     * Reads and returns the next line of input from the user.
     *
     * @return The input command entered by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        return command;
    }

    /**
     * Builds a message enclosed within separator lines.
     *
     * @param lines The lines to include in the message.
     * @return The full message string.
     */
    private String buildMessage(String... lines) {
        StringBuilder message = new StringBuilder(LINE_SEPARATOR).append("\n");
        for (String line : lines) {
            message.append(line).append("\n");
        }
        message.append(LINE_SEPARATOR);
        return message.toString();
    }

    /**
     * Builds a task action message (e.g., task added, task removed).
     *
     * @param actionMessage The action message (e.g., "Got it. I've added this task:").
     * @param task The task being acted upon.
     * @param size The current number of tasks (optional).
     * @return The StringBuilder containing the full message.
     */
    private StringBuilder buildTaskActionMessage(String actionMessage, Task task, int... size) {
        StringBuilder taskMessage = new StringBuilder(LINE_SEPARATOR).append("\n");
        taskMessage.append(actionMessage);
        taskMessage.append(" ").append(task).append("\n");
        if (size.length > 0) {
            taskMessage.append(" Now you have ").append(size[0]).append(" tasks in the list.\n");
        }
        taskMessage.append(LINE_SEPARATOR);
        return taskMessage;
    }

    /**
     * Prints the message to the console.
     *
     * @param message The message to be printed.
     */
    private void printMessage(String message) {
        System.out.println(message);
    }
}
