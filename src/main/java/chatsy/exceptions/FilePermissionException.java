package chatsy.exceptions;

/**
 * Represents an exception that is thrown when there is an issue with file permissions.
 */
public class FilePermissionException extends LocalFileException {

    /**
     * Constructs a {@code FilePermissionException} with the specified file path.
     *
     * @param filePath The path of the file with permission issues.
     */
    public FilePermissionException(String filePath) {
        super("Oops, an error occurred when creating/writing a local file. The file path is " + filePath);
    }
}
