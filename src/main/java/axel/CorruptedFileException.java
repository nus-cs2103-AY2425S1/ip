package axel;

public class CorruptedFileException extends Exception {
    public CorruptedFileException(String message) {
        super(message);
    }
}