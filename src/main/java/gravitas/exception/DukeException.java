package gravitas.exception;

/**
 * Represents the exception class for error handling.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param msg Error message.
     */
    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
