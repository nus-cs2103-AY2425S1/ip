package stobberi.stobberiexception;

/**
 * Custom exception class for Stobberi application.
 * <p>
 * This class is a subclass of the {@link java.lang.Exception} and is used to
 * represent custom exceptions specific to the Stobberi application.
 * </p>
 */
public class StobberiException extends Exception {

    /**
     * Constructs a new StobberiException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by
     *                the {@link #getMessage()} method.
     */
    public StobberiException(String message) {
        super("UMmmmmm, I'm weallly sowwyyy!!!!\n\n" + message);
    }
}
