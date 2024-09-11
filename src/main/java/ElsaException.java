/**
 * Represents exceptions specific to Elsa.
 * This custom exception is thrown when an error related to Elsa's operations occur.
 *
 * @author Aaron
 */
public class ElsaException extends Exception {
    /**
     * Constructs a new ElsaException with the specified detail message.
     *
     * @param message The detail message of the exception.
     */
    public ElsaException(String message) {
        super(message);
    }

    /**
     * Adds a line above and below error messages for aesthetic purposes.
     *
     * @param message The error message.
     * @return A string with the separator lines added above and below the error message.
     */
    public static String addSeparatorLines(String message) {
        String separator = "______________________________________";
        return separator + "\n" + message + "\n" + separator;
    }
}
