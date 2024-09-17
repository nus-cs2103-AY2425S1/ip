package tasklist;

import ouiouibaguette.OuiOuiBaguetteException;

/**
 * Represents an exception that is thrown when an attempt is made to access an element
 * at an index that is out of bounds in the task list.
 */
public class TaskListOutOfBoundsException extends OuiOuiBaguetteException {

    /**
     * Constructs a TaskListOutOfBoundsException with a detailed error message.
     * The message includes the index that was accessed and the size of the task list.
     *
     * @param index The index that was out of bounds.
     * @param size  The size of the task list.
     */
    public TaskListOutOfBoundsException(int size) {
        super("Invalid index for Tasklist of size " + size);
    }
}
