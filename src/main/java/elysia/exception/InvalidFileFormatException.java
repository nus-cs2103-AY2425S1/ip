package elysia.exception;

/**
 * Represents an invalid date format exception.
 */
public class InvalidFileFormatException extends ElysiaException {
    public InvalidFileFormatException() {
        super("Oh my! I'm so sorry,\n"
                + "but it seems the file is corrupted.\n"
                + "Please fix or delete the Elysia.txt.");
    }
}
