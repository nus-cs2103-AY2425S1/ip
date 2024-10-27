package fred.Exceptions;

/**
 * The FredException class is the base class for all custom exceptions in the Fred application.
 * It extends the standard Exception class and is used to provide meaningful error messages.
 */
public class FredException extends Exception {

    /**
     * Constructs a FredException with a specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public FredException(String message) {
        super(message);
    }
}
