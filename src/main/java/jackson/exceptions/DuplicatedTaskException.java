package jackson.exceptions;

/**
 * Exception to handle the case when user enters same task name more than once.
 */
public class DuplicatedTaskException extends JacksonException {
    public DuplicatedTaskException(String msg) {
        super(msg);
    }
}
