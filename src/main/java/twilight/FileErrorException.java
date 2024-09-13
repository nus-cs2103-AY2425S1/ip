package twilight;

/**
 * Handles issues with file loading.
 */
public class FileErrorException extends Exception {
    private final String message;

    /**
     * Instantiates a file error exception.
     *
     * @param message Error message.
     */
    public FileErrorException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}