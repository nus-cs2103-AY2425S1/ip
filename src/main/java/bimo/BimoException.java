package bimo;

/**
 * Represents a custom Checked Exception.
 * Thrown when there are errors involving commands.
 */
public class BimoException extends Exception {
    public BimoException(String message) {
        super(message);
    }
}
