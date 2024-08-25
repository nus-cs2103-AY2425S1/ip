package orionExceptions;

public class FileInitializationException extends OrionException {

    public FileInitializationException(String message) {
        super("File Initialization Error: " + message);
    }
}
