package bruno.exceptions;

/**
 * Exception should be thrown when the data file can not be loaded properly.
 */
public class FileLoadingException extends BrunoException {
    public FileLoadingException() {
        super("There was an error loading the data file.");
    }
}
