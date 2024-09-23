package lewis;

/**
 * This class implements a custom exception that Lewis may catch while
 * executing or parsing commands
 */
public class LewisException extends Exception {
    public LewisException(String errorMessage) {
        super(errorMessage);
    }
}
