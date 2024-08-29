package dgpt.exception;

/**
 * Thrown to indicate that a specific file was not found.
 * This exception is used by the {@code Storage} class when it cannot locate the file specified.
 */
public class DgptFileNotFoundException extends Exception {
    public DgptFileNotFoundException(String message) {
        super(message);
    }
}
