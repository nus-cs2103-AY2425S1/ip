package guy.exception;

/**
 * Custom Exception class for errors unique to ThatOneGuy.
 */
public class GuyException extends Exception {
    /**
     * Constructs a GuyException with the given message.
     *
     * @param e error message to be saved for retrieval by getMessage()
     */
    public GuyException(String e) {
        super(e);
    }
}
