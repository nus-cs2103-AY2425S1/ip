package exception;

/**
 * Represents an exception thrown when a file cannot be found.
 * The {@code FileNotFoundKukiShinobuException} is a specific type of
 * {@link KukiShinobuException} used to indicate that a file was not
 * found at the expected location.
 */
public class FileNotFoundKukiShinobuException extends KukiShinobuException {

    /**
     * Constructs a {@code FileNotFoundKukiShinobuException} with a default error message.
     * The message indicates that the file could not be found and suggests checking the file path.
     */
    public FileNotFoundKukiShinobuException() {
        super("Hmm, can't seem to find the file. Better check the path!");
    }
}
