package bruno.exceptions;

public class FileLoadingException extends BrunoException {
    public FileLoadingException() {
        super("There was an error loading the data file.");
    }
}