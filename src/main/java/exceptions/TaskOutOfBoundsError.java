package exceptions;

/**
 * Exception that indicates that the index provided by the user is out of range of the taskList
 *
 */
public class TaskOutOfBoundsError extends Exception {
    /**
     * Takes in the index given by the user.
     * Informs user that the index provided is out of range
     *
     * @param index The index provided by the user.
     */
    public TaskOutOfBoundsError(int index) {
        super("Error: task " + index + " does not exist");
    }
}
