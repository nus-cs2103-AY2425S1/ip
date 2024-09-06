package stan.ui;

import java.util.List;
import java.util.Scanner;

import stan.TaskList;
import stan.tasks.Task;


/**
 * The Ui class handles interactions with the user.
 * It provides methods to read user input, display output messages, and show errors.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for Ui.
     * Initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The full command string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns the welcome message when the chatbot starts.
     */
    public String showWelcome() {
        return "Hello! I'm Stan\nWhat can I do for you today?";
    }

    /**
     * Returns the goodbye message when the chatbot exits.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     * @return The message as a string.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n" + "   " + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     * @return The message as a string.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        return "Noted. I've removed this task:\n" + "   " + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @param taskCount The current number of tasks in the list.
     * @return The message as a string.
     */
    public String showTaskMarked(Task task, int taskCount) {
        return "Nice! I've marked this task as done:\n" + "   " + task + "\n"
                + "You now have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns a message indicating that a task has been unmarked as not done.
     *
     * @param task The task that was unmarked.
     * @param taskCount The current number of tasks in the list.
     * @return The message as a string.
     */
    public String showTaskUnmarked(Task task, int taskCount) {
        return "OK, I've marked this task as not done yet:\n" + "   " + task + "\n"
                + "You now have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns the current list of tasks as a string.
     *
     * @param taskList The list of tasks to be displayed.
     * @return The list of tasks as a string.
     */
    public String showTaskList(TaskList taskList) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns an error message.
     *
     * @param message The error message to be displayed.
     * @return The error message as a string.
     */
    public String showError(String message) {
        return "OOPS!!! " + message;
    }

    /**
     * Returns the tasks that match the find keyword as a string.
     *
     * @param matchingTasks The list of matching tasks.
     * @return The list of matching tasks as a string.
     */
    public String showFindResults(List<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(" ").append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
