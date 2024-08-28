package Kotori.Storage;

public class CorruptedFileException extends Exception {
    public CorruptedFileException(String message) {
        super(message);
    }
}
