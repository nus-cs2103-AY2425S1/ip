package strand.exception;

/**
 * Thrown when the file cannot be found.
 */
public class StrandFileNotFoundException extends StrandException {
    @Override
    public String getMessage() {
        return "File not found " + super.getMessage();
    }
}
