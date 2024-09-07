package exceptions;

/**
 * Thrown when Him attempts to retrieve a task from an index that does not exist.
 *
 * @author IsaacPangTH
 */
public class TaskDoesNotExistException extends HimException {

    /**
     * Constructor for<code>TaskDoesNotExistException</code>.
     *
     * @param index Index Him is trying to access.
     */
    public TaskDoesNotExistException(int index) {
        super("task " + (index + 1) + " does not exist");
    }
}
