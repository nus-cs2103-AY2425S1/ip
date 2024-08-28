package evan.exception;

public class FileCreationException extends Exception {
    public FileCreationException(String filePath) {
        super("Oh no! There was an error when trying to create the file: '" + filePath + "'");
    }

}
