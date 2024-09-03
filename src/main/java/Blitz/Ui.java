package blitz;

import task.Task;

/**
 * Handles UI operations related to printing messages.
 */
public class Ui {
    /**
     * Prints specified (added) Task object as String.
     *
     * @param type Type of the Task object.
     * @param size Size of the current TaskList.
     * @param task Task object to be printed.
     */
    public String printTaskAdded(String type, int size, Task task) {
        return "Got it. I've added this task:\n"
                + "  [" + type + "][ ] " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }
}
