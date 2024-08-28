package chatsy.exceptions;

/**
 * Represents an exception that is thrown when there is an issue with file permissions.
 */
public class FilePermissionException extends LocalFileException {
    private String filePath;

    /**
     * Constructs a {@code FilePermissionException} with the specified file path.
     *
     * @param filePath The path of the file with permission issues.
     */
    public FilePermissionException(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    /**
     * Returns a string representation of the error message indicating a file permission issue.
     *
     * @return The error message as a string.
     */
    @Override
    public String toString() {
        return "Oops, an error occurred when creating / writing a local file.\n"
                + "The file path is " + this.filePath;
    }
}
