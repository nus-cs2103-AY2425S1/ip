package orion.orionexceptions;

/**
 * Exception thrown when an invalid index is accessed in a task list.
 *
 * <p>
 * This exception is thrown when the index accessed is out of range of the
 * available tasks.
 * </p>
 *
 * @author Pradyumna
 */
public class InvalidIndexException extends OrionException {

    /**
     * Creates a new InvalidIndexException with a message showing the invalid index
     * and the valid range of indices.
     *
     * @param indexAccessed the index that was attempted to be accessed.
     * @param maxIndex      the maximum valid index in the task list.
     */
    public InvalidIndexException(int indexAccessed, int maxIndex) {
        super(maxIndex == 0 ? "You tried to access " + indexAccessed + ". But the task list is empty."
                : "You tried to access " + indexAccessed + ". But we have only " + maxIndex
                        + " tasks, and you should access between 1 and that instead.");
    }
}
