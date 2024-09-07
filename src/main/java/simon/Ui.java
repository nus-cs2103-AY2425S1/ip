package simon;

import java.util.ArrayList;

/**
 * Provides methods for displaying messages and interacting with the user.
 * This includes showing welcome messages, task lists, error messages, and other user interactions.
 */
public class Ui {
    static final String HOR_LINE = "\t____________________________________________________________\n";
    static final String WLC_MSG = " Hello! I'm simon.Simon \n"
            + " What can I do for you?\n";
    static final String EXT_MSG = "Bye. Hope to see you again soon!";

    /**
     * Displays a welcome message and returns it.
     *
     * @return The welcome message.
     */
    public static String showWelcome() {
        System.out.println(WLC_MSG);
        return WLC_MSG;
    }

    /**
     * Displays an exit message and returns it.
     */
    public void showExit() {
        System.out.print(printMessage(EXT_MSG));
    }

    /**
     * Displays an error message related to loading and returns it.
     *
     * @param e The error that occurred.
     * @return A string indicating the error that occurred.
     */
    public String showError(Error e) {
        System.out.print(printMessage("An error occurred while loading: " + e.getMessage()));
        return "An error occurred while loading: " + e.getMessage();
    }

    /**
     * Displays the list of tasks and returns a string representation of the task list.
     *
     * @param tasks The task list to be displayed.
     * @return A string representation of the task list.
     */
    public String showTaskList(TaskList tasks) {
        assert tasks != null : "tasks cannot be null";
        StringBuilder str = new StringBuilder();
        System.out.print(HOR_LINE);
        str.append("Here are your tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());
            str.append((i + 1) + ". " + task.toString() + "\n");
        }
        System.out.print(HOR_LINE);
        return str.toString();
    }

    /**
     * Reads a command from the provided message.
     *
     * @param msg The message to read.
     * @return The read message.
     */
    public String readCommand(String msg) {
        return msg;
    }

    /**
     * Displays a message indicating that a task has been added and returns it.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     * @return A message indicating that the task was added and the new size of the list.
     */
    public String showTaskAdded(Task task, Integer size) {
        System.out.print(printMessage("Got it. I've added this task: \n" + "\t" + task.toString())
                + "\tNow you have " + size + " tasks in the list.");
        return "Got it. I've added this task: \n" + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been marked as done and returns it.
     *
     * @param task The task that was marked as done.
     * @return A message indicating that the task was marked as done.
     */
    public String showTaskMarked(Task task) {
        assert task != null : "task cannot be null";
        System.out.print(printMessage("\tNice! I've marked this task as done:\n" + "\t" + task.toString()));
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as not done yet and returns it.
     *
     * @param task The task that was marked as not done yet.
     * @return A message indicating that the task was marked as not done yet.
     */
    public String showTaskUnmarked(Task task) {
        assert task != null : "task cannot be null";
        System.out.print(printMessage("\tOK, I've marked this task as not done yet:\n" + "\t" + task.toString()));
        return "OK, I've marked this task as not done yet: \n" + task.toString();
    }

    /**
     * Displays a message indicating that a task has been deleted and returns it.
     *
     * @param task The task that was deleted.
     * @param taskCount The new number of tasks in the list.
     * @return A message indicating that the task was deleted and the new task count.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        assert task != null : "task cannot be null";
        System.out.print(printMessage("Noted. I've removed this task:\n" + "\t" + task.toString())
                + "\tNow you have " + taskCount + " tasks in the list.");
        return ("Noted. I've removed this task: " + task.toString())
                + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Prints a message enclosed within horizontal lines.
     *
     * @param msg The message to be printed.
     * @return The formatted message enclosed within horizontal lines.
     */
    private String printMessage(String msg) {
        return HOR_LINE + "\t" + msg + "\n" + HOR_LINE;
    }

    /**
     * Displays a loading error message and returns it.
     */
    public void showLoadingError() {
        System.out.println("Error in loading");
    }

    /**
     * Displays a duplicate error message and returns it.
     */
    public String showDuplicate() {
        System.out.println("Duplicate task");
        return "Duplicate task";
    }

    /**
     * Displays the search results for tasks and returns a string representation.
     *
     * @param tasks The list of tasks that match the search criteria.
     * @return A string representation of the matching tasks.
     */
    public String showSearchTask(ArrayList<Task> tasks) {
        StringBuilder str = new StringBuilder();
        System.out.println(HOR_LINE);
        str.append("Here are the matching tasks in your list:");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            str.append("\n").append(i + 1).append(". ").append(task.toString());
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
        System.out.print(HOR_LINE);
        return str.toString();
    }
}
