package ip.derrick;

/**
 * Exception class to handle inputs missing a position integer.
 */
public class MissingPositionException extends Exception {
    public MissingPositionException(String message) {
        super(message);
    }
}
