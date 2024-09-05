package stobberi.stobberiexception;

/**
 * Custom exception for cases where a Stobberi task is not found.
 *
 * <p>This exception extends {@link StobberiException} and indicates that
 * an operation failed because the specified Stobberi task does not exist.</p>
 */
public class NoSuchTaskStobberiException extends StobberiException {

    /**
     * Constructs a new NoSuchTaskStobberiException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by
     *                the {@link #getMessage()} method.
     */
    public NoSuchTaskStobberiException(String message) {
        super(message);
    }
}
