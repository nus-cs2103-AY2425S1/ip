package friday.util;

import java.util.Scanner;

import friday.task.Task;
import friday.task.TaskList;

/**
 * Handles user interface interactions for the Friday task manager.
 * Manages displaying messages to the user and reading user input.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "Welcome Back! I'm Friday.\nWhat can I do for you today?"
                + "\nTo view the list of commands that I support, type 'help' for more information.";
    }

    /**
     * Displays a help message listing all supported commands.
     *
     * @return {@code true} to indicate that the help message was shown.
     */
    public String showHelp() {
        return "Here are the list of commands that I support:" + getHelpCommand()
                + getListCommand()
                + getMarkCommand()
                + getUnmarkCommand()
                + getTodoCommand()
                + getDeadlineCommand()
                + getEventCommand()
                + getDeleteCommand()
                + getFindCommand()
                + getByeCommand();
    }

    private String getHelpCommand() {
        return "\nhelp - List of commands supported by me, Friday.";
    }

    private String getListCommand() {
        return "\nlist - View all entries to the current list of things you have asked me to take note of.";
    }

    private String getMarkCommand() {
        return "\nmark <integer> - Mark an entry in the list as a completed task.";
    }

    private String getUnmarkCommand() {
        return "\nunmark <integer> - Unmark an entry in the list as a completed task.";
    }

    private String getTodoCommand() {
        return "\ntodo <string> - Remember a TODO task for you to revisit again.";
    }

    private String getDeadlineCommand() {
        return "\ndeadline <string> /by <yyyy-mm-dd hhmm> -"
                + " Remember a Deadline task for you to complete by the deadline.";
    }

    private String getEventCommand() {
        return "\nevent <string> /from <yyyy-mm-dd hhmm> /to <yyyy-mm-dd hhmm> -"
                + " Remember an Event task from when it begins to when it ends.";
    }

    private String getDeleteCommand() {
        return "\ndelete <integer> - Delete an entry from your current list.";
    }

    private String getFindCommand() {
        return "\nfind <string> - Find tasks by a keyword from your current list.";
    }

    private String getByeCommand() {
        return "\nbye - Exits this app and says Good Bye to Friday :).";
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
     * @return The string representation of tasks in the task list.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            response.append("\n").append(i + 1).append(".").append(tasks.getTasks().get(i).toString());
        }
        return response.toString();
    }

    /**
     * Displays a goodbye message to the user.
     * @return The string representation of the goodbye message to the user.
     */
    public String showGoodbye() {
        return "Good Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The {@link Task} that was added.
     * @param taskCount The updated number of tasks in the list.
     * @return The string representation of a task being added to the task list.
     */
    public String showTaskAdded(Task task, int taskCount) {
        String response = "Got it. I've added this task:";
        response += "\n\t" + task + "\nNow you have " + taskCount + " tasks in the list.";
        return response;
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The {@link Task} that was deleted.
     * @param taskCount The updated number of tasks in the list.
     * @return The string representation of a task being deleted from the task list.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        String response = "Noted. I've removed this task:";
        response += "\n\t" + task + "\nNow you have " + taskCount + " tasks in the list.";
        return response;
    }

    public String showUpdateSuccess(Task task) {
        return "Successfully updated the task:\n\t" + task;
    }

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
