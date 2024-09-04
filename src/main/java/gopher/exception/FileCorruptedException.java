package gopher.exception;

/**
 * Thrown if the file handled during the operation can't be parsed or read.
 */
public class FileCorruptedException extends Exception {
    public FileCorruptedException(String message) {
        super(message);
    }
}
