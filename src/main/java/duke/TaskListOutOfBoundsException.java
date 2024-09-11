package duke;

/**
 * This exception is thrown whenever the Task List is indexed out of its range.
 */
public class TaskListOutOfBoundsException extends Exception {
    @Override
    public String getMessage() {
        return "Please select an item number from within the current list for deletion.";
    }
}
