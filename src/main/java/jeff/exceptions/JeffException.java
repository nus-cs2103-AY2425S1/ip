package jeff.exceptions;

/**
 * Represents a custom exception specific to the application's error handling.
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class JeffException extends Exception {
    public JeffException(String message) {
        super(message);
    }
}
