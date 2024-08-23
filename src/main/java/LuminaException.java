/**
 * Lumina exception, is a class extending from exception, which encapsulates special exceptions
 * that only occur in Lumina
 */
public class LuminaException extends Exception {
    public LuminaException(String message) {
        super(message);
    }
}
