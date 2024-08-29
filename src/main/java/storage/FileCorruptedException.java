package storage;

public class FileCorruptedException extends Exception {

    public FileCorruptedException(String message) {
        super(message);
    }
}
