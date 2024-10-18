package beeboo.exception;

/**
 * Represents a custom exception for the BeeBoo chatbot application.
 * This class serves as the base exception class for all exceptions specific to BeeBoo.
 */
public class BeeBooExceptions extends Exception {

    /**
     * Constructs a BeeBooExceptions with a specific error message.
     *
     * @param error The error message associated with this exception.
     */
    public BeeBooExceptions(String error) {
        super(error);
    }
}
