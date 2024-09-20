package totoro.exception;

/**
 * Represents an exception that is thrown when an issue occurs while reading from a file
 * This exception is part of the custom exceptions in the Totoro application and is used to handle file-related errors
 */
public class TotoroFileException extends TotoroException {
    @Override
    public String toString() {
        return String.format("%s Cannot read from file", super.toString());
    }
}
