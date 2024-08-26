package friday.util;

import friday.task.Task;
import friday.task.TaskList;

import java.util.Scanner;

/**
 * Handles user interface interactions for the Friday task manager.
 * Manages displaying messages to the user and reading user input.
 */
public class Ui {

    /**
     * Displays a line separator in the console.
     */
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("\tWelcome Back! I'm Friday.");
        System.out.println("\tWhat can I do for you today?");
        System.out.println("\tTo view the list of commands that I support, type 'help' for more information.");
        showLine();
    }

    /**
     * Displays a help message listing all supported commands.
     *
     * @return {@code true} to indicate that the help message was shown.
     */
    public boolean showHelp() {
        System.out.println("\tHere are the list of commands that I support");
        System.out.println("\thelp - List of commands supported by me, Friday.");
        System.out.println("\tlist - View all entries to the current list of things" +
                " you have asked me to take note of.");
        System.out.println("\tmark <integer> - Mark an entry in the list as a completed task.");
        System.out.println("\tunmark <integer> - Unmark an entry in the list as a completed task.");
        System.out.println("\ttodo <string> - Remember a TODO task for you to revisit again.");
        System.out.println("\tdeadline <string> /by <yyyy-mm-dd hhmm> - Remember a Deadline task for" +
                " you to complete by the deadline.");
        System.out.println("\tevent <string> /from <yyyy-mm-dd hhmm> /to <yyyy-mm-dd hhmm> - Remember an Event task" +
                " from when it begins to when it ends.");
        System.out.println("\tdelete <integer> - Delete an entry from your current list.");
        System.out.println("\tbye - Exits this app and says Good Bye to Friday :)");
        return true;
    }

    /**
     * Displays an error message when there is an issue loading tasks.
     */
    public void showLoadingError() {
        System.out.println("An error occurred while loading tasks.");
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The {@link TaskList} containing the tasks to be displayed.
     * @return {@code true} to indicate that the task list was shown.
     */
    public boolean showTaskList(TaskList tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getTasks().get(i).toString());
        }
        return true;
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("\tGood Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The {@link Task} that was added.
     * @param taskCount The updated number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The {@link Task} that was deleted.
     * @param taskCount The updated number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    private final Scanner sc = new Scanner(System.in);

    /**
     * Reads a command from the user input.
     *
     * @return The command read from the user input, or "bye" if no input is available.
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "bye";
        }
    }
}
