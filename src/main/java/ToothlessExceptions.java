/**
 * ToothlessExceptions class is a custom exception class that extends the Exception class.
 */
public class ToothlessExceptions extends Exception {

    /**
     * Constructor for ToothlessExceptions.
     * @param message the error message to be displayed
     */
    public ToothlessExceptions(String message) {
        super("Toothless:\nOops! Some error has occured! X.X\n\n" + message);
    }
}
