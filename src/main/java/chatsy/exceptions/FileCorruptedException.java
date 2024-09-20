package chatsy.exceptions;

/**
 * Represents an exception that is thrown when a file is corrupted.
 */
public class FileCorruptedException extends LocalFileException {

    /**
     * Constructs a {@code FileCorruptedException} with the specified file path.
     *
     * @param filePath The path of the corrupted file.
     */
    public FileCorruptedException(String filePath) {
        super("Oops, the file at " + filePath + " seems to be corrupted.");
    }
}
