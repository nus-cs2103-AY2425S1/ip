package Cookie;
public class CookieException extends Exception {
    /**
     * Constructor for CookieException.
     *
     * @param errorMessage message to be displayed to the user when thrown.
     */
    public CookieException(String errorMessage) {
        super(errorMessage);
    }
}
