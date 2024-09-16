package nerf.error;

/**
 * Error class for file I/O-related issues.
 */

public class FilePermissionsException extends Exception {
    public FilePermissionsException(String errorMessage) {
        super(errorMessage);
    }
}
