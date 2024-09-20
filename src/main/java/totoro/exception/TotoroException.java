package totoro.exception;

/**
 * Represents a custom exception used in the Sage application
 */
public abstract class TotoroException extends Exception {
    @Override
    public String toString() {
        return "Error: ";
    }
}
