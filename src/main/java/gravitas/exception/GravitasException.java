package gravitas.exception;

/**
 * Represents the exception class for error handling.
 */
public class GravitasException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param msg Error message.
     */
    public GravitasException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
