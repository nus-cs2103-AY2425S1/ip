package evan.exception;

/**
 * Represents an exception that is thrown when a file cannot be created.
 */
public class FileCreationException extends Exception {
    /**
     * Instantiates a FileCreationException object.
     *
     * @param filePath File path of the file that was being created.
     */
    public FileCreationException(String filePath) {
        super("Oh no! There was an error when trying to create the file: '" + filePath + "'");
    }

}
