package totoro.exception;

/**
 * Represents an exception that is thrown when the file contains an unknown or invalid task type
 * This exception is part of the custom exception handling in the Totoro application
 */
public class TotoroFileFormatException extends TotoroException {
    @Override
    public String toString() {
        return String.format("%s Unknown task type", super.toString());
    }
}
