package cow.exceptions;

/**
 * Represents exceptions specific to the Cow application.
 */
public class CowExceptions extends Exception {
    /**
     * Creates a CowExceptions instance.
     *
     * @param exception the exception message.
     */
    public CowExceptions(String exception) {
        super(exception);
    }
}
