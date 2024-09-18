package colress.exception;

/**
 * An exception that is thrown when there is an error reading the text file containing the tasks.
 */
public class FileCorruptedException extends Exception {
    public FileCorruptedException() {
        super("What is this?! The task file seems to be corrupted! Delete the file and try again.");
    }
}
