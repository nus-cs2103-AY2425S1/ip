package chatsy.exceptions;

/**
 * Represents an exception that is thrown when a file is corrupted.
 */
public class FileCorruptedException extends LocalFileException {
    private String filePath;

    /**
     * Constructs a {@code FileCorruptedException} with the specified file path.
     *
     * @param filePath The path of the corrupted file.
     */
    public FileCorruptedException(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    /**
     * Returns a string representation of the error message indicating the file is corrupted.
     *
     * @return The error message as a string.
     */
    @Override
    public String toString() {
        return "OOPS, the file " + this.filePath + " seems to be corrupted.\n";
    }
}
