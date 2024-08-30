/**
 * This exception is thrown when a task is not found in the task list.
 */
package duke.exceptions;
public class TaskNotFoundException extends Exception {
    @Override
    public String toString() {
        return "OOPS!!! The task cannot be found.";
    }
}
