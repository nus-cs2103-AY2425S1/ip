package chatsy.exceptions;

/**
 * Represents a general exception related to local file operations in the Chatsy application.
 */
public class LocalFileException extends ChatsyException {

    private final String filePath;

    /**
     * Constructs a {@code LocalFileException} with the specified file path and a default error message.
     *
     * @param filePath The path of the file causing the exception.
     */
    public LocalFileException(String filePath) {
        super("Oops, an error occurred with the file at " + filePath);
        this.filePath = filePath;
    }

}
