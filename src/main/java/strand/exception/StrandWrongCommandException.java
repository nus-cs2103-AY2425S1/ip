package strand.exception;

/**
 * Thrown when an unrecognized command is encountered.
 */
public class StrandWrongCommandException extends StrandException {
    @Override
    public String getMessage() {
        return "Command not found " + super.getMessage();
    }
}
