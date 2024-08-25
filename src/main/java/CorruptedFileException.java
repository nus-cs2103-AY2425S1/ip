public class CorruptedFileException extends RuntimeException {
    public CorruptedFileException() {
        super("The file is corrupted");
    }
}
