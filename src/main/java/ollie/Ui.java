package ollie;

import ollie.exception.OllieException;
import ollie.task.Task;

/**
 * User Interface, responsible for returning response message to the user.
 */
public class Ui {

    /**
     * Return the greeting message.
     */
    public String getGreetingMessage() {
        return "Hello! I'm Ollie!\nWhat can I do for you?";
    }

    /**
     * Return the exit message.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Return message containing the list of task.
     *
     * @param tasks List of tasks.
     */
    public String getTaskListMessage(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Return message containing the task added and the current size of its list of tasks.
     */
    public String getAddTaskMessage(Task task, int size) {
        return ("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + size + " tasks in the list."
        );
    }

    /**
     * Return message containing the task removed and the current size of its list of tasks.
     */
    public String getRemoveTaskMessage(Task task, int size) {
        return ("Noted. I've removed this task:\n  "
                + task.toString()
                + "\nNow you have " + size + " tasks in the list."
        );
    }

    /**
     * Return message containing the task that was marked as done.
     */
    public String getMarkAsDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task.toString();
    }

    /**
     * Return message containing the task that was marked as undone.
     */
    public String getMarkAsUndoneMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task.toString();
    }

    /**
     * Return the error message for any error during the loading of data from the file (database).
     */
    public String getLoadingErrorMessage(OllieException e) {
        return ("Loading Error:\n" + e.getMessage());
    }

}
