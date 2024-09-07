package cookie.exception;
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
