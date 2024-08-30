package exception;

public class InvalidStorageFileException extends LevelHundredException {
    public InvalidStorageFileException() {
        super("File format wrong");
    }
}
