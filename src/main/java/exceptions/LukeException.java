package exceptions;

/**
 * Exception class for exception thrown while running Luke program.
 */
public class LukeException extends Exception {
    public LukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
