package chatsy.exceptions;

/**
 * Represents a general exception related to local file operations in the Chatsy application.
 */
public class LocalFileException extends ChatsyException {
    private String filePath;

    /**
     * Constructs a {@code LocalFileException} with the specified file path.
     *
     * @param filePath The path of the file causing the exception.
     */
    public LocalFileException(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a string representation of the error message indicating a local file issue.
     *
     * @return The error message as a string.
     */
    @Override
    public String toString() {
        return "OOPS, an error occurred with file " + this.filePath;
    }
}
