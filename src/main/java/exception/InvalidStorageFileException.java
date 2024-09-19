package exception;

/**
 * The InvalidStorageFileException class is used for issues with the storage file
 */
public class InvalidStorageFileException extends LevelHundredException {
    public InvalidStorageFileException() {
        super("File format wrong");
    }
}
