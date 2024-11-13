package elysia.exception;

/**
 * Represents an invalid file format exception.
 */
public class InvalidFileFormatException extends ElysiaException {
    /**
     * Constructs an {@code InvalidFileFormatException} with a message indicating that the file is corrupted.
     */
    public InvalidFileFormatException() {
        super("Oh my! I'm so sorry,\n"
                + "but it seems the file is corrupted.\n"
                + "Please fix or delete the Elysia.txt.");
    }
}
