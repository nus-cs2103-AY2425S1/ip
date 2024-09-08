package toothless.exceptions;

/**
 * ToothlessExceptions class is a custom exception class that extends the Exception class.
 */
public class ToothlessExceptions extends Exception {

    /**
     * Constructor for ToothlessExceptions.
     *
     * @param message the error message to be displayed
     */
    public ToothlessExceptions(String message) {
        super("Oops! Some error has occurred! X.X\n\n" + message);
    }
}
