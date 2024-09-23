package makima.exception;

/**
 * Corrupted File Exception
 */
public class FileCorruptedException extends RuntimeException {
    public FileCorruptedException(int lineNumber) {
        super(String.format("The file is corrupted at line %s. Please delete it before restarting!", lineNumber));
    }
}
