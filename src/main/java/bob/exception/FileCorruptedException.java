package bob.exception;

/**
 * This exception is thrown when the data file is corrupted.
 */
public class FileCorruptedException extends BobException {
    public FileCorruptedException() {
        super("Yikes, looks like the data file's busted.\n" +
                "We're just gonna start with a fresh list.");
    }
}
