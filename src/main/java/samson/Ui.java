package samson;

import samson.task.Task;
import samson.task.TaskList;

import java.util.List;
import java.util.Scanner;

/**
 * The <code> Ui </code> class handles all interactions with the user.
 * It manages input from the user and output to the console,
 * including displaying messages and task information.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new <code> Ui </code> object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the chatbot starts.
     *
     * @return The welcome message as a String.
     */
    public static String welcomeMessage() {
        return " Hello! I'm Samson\n" + " What can I do for you?\n";
    }

    /**
     * Displays the goodbye message when the chatbot ends.
     *
     * @return The goodbye message as a String.
     */
    public String goodbyeMessage() {
        return " Bye. Hope to see you again soon!\n";
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user as a <code> String </code>.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns a horizontal line, used to separate different sections of the output.
     *
     * @return A line separator as a String.
     */
    public static String showLine() {
        return "____________________________________________________________\n";
    }

    /**
     * Returns an error message to the user.
     *
     * @param message The error message to be displayed.
     * @return The error message as a String.
     */
    public String showError(String message) {
        return " OOPS!!! " + message + "\n";
    }

    /**
     * Returns an error message when there is a failure in loading tasks from the file.
     *
     * @return The loading error message as a String.
     */
    public String showLoadingError() {
        return "Error loading tasks from file.\n";
    }

    /**
     * Returns the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     * @return The list of tasks as a String.
     */
    public String showTaskList(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(showLine());
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(" " + (i + 1) + ". " + taskList.get(i) + "\n");
        }
        sb.append(showLine());
        return sb.toString();
    }

    /**
     * Returns a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param taskList The updated list of tasks.
     * @return The task added message as a String.
     */
    public String showTaskAdded(Task task, TaskList taskList) {
        return " Got it. I've added this task:\n" +
                "   " + task + "\n" +
                " Now you have " + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Returns a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param taskList The updated list of tasks.
     * @return The task deleted message as a String.
     */
    public String showTaskDeleted(Task task, TaskList taskList) {
        return " Noted. I've removed this task:\n" +
                "   " + task + "\n" +
                " Now you have " + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return The task marked message as a String.
     */
    public String showTaskMarked(Task task) {
        return " Nice! I've marked this task as done:\n" +
                "   " + task + "\n";
    }

    /**
     * Returns a message indicating that a task has been unmarked as not done.
     *
     * @param task The task that was unmarked.
     * @return The task unmarked message as a String.
     */
    public String showTaskUnmarked(Task task) {
        return " OK, I've marked this task as not done yet:\n" +
                "   " + task + "\n";
    }

    /**
     * Returns an error message when the task number provided by the user is invalid.
     *
     * @return The invalid task number message as a String.
     */
    public String showTaskNumInvalid() {
        return " Task number out of range.\n";
    }

    /**
     * Returns the results of a find operation.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     * @return The find results as a String.
     */
    public String showFindResults(List<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(showLine());
        if (matchingTasks.isEmpty()) {
            sb.append("No matching tasks found.\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append((i + 1) + "." + matchingTasks.get(i) + "\n");
            }
        }
        sb.append(showLine());
        return sb.toString();
    }
}
