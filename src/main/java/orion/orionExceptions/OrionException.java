package orion.orionexceptions;

/**
 * Base class for all exceptions in the Orion application.
 *
 * <p>
 * This exception is used as a parent class for more specific exceptions.
 * </p>
 *
 * @author Pradyumna
 */
public class OrionException extends Exception {

    /**
     * Constructs a new OrionException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public OrionException(String message) {
        super("The program threw an exception. It is: " + message);
    }
}
