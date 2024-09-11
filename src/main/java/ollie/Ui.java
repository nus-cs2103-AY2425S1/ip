package ollie;

import ollie.exception.OllieException;
import ollie.task.Task;

/**
 * User Interface, responsible for returning response message to the user.
 */
public class Ui {

    /**
     * Returns the greeting message.
     */
    public String getGreetingMessage() {
        return "Hello! I'm Ollie!\nWhat can I do for you?";
    }

    /**
     * Returns the exit message.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns message containing the list of task.
     *
     * @param tasks List of tasks.
     */
    public String getTaskListMessage(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Returns message containing the task added and the current size of its list of tasks.
     */
    public String getAddTaskMessage(Task task, int size) {
        return ("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + size + " tasks in the list."
        );
    }

    /**
     * Returns message containing the task removed and the current size of its list of tasks.
     */
    public String getRemoveTaskMessage(Task task, int size) {
        return ("Noted. I've removed this task:\n  "
                + task.toString()
                + "\nNow you have " + size + " tasks in the list."
        );
    }

    /**
     * Returns message containing the task that was marked as done.
     */
    public String getMarkAsDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task.toString();
    }

    /**
     * Returns message containing the task that was marked as undone.
     */
    public String getMarkAsUndoneMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task.toString();
    }

    /**
     * Returns the error message for any error during the loading of data from the file (database).
     */
    public String getLoadingErrorMessage(OllieException e) {
        return ("Loading Error:\n" + e.getMessage());
    }

}
