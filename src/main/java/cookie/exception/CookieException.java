package cookie.exception;

/**
 * Represents an exception that is thrown in response to errors specific to the cookie application.
 */
public class CookieException extends Exception {
    /**
     * Constructs a new {@code CookieException} with the specified detail message.
     *
     * @param errorMessage the detail message to be displayed to the user when the exception is thrown
     */
    public CookieException(String errorMessage) {
        super(errorMessage);
    }
}
