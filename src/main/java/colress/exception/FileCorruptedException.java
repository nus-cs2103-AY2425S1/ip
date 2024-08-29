package colress.exception;

public class FileCorruptedException extends Exception {
    public FileCorruptedException() {
        super("The task file seems to be corrupted. Delete the file and try again.");
    }
}
