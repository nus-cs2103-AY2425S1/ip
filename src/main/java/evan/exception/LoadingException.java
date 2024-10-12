package evan.exception;

/**
 * Represents an exception that is thrown when the saved tasks cannot be loaded.
 */
public class LoadingException extends Exception {
    /**
     * Instantiates a LoadingException object.
     *
     * @param message Message that describes why the saved tasks couldn't be loaded.
     */
    public LoadingException(String message) {
        super("Oh no! There was an error loading the saved tasks: " + message);
    }
}
