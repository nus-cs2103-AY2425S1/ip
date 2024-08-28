package evan.exception;

public class LoadingException extends Exception {
    public LoadingException(String message) {
        super("Oh no! There was an error loading the saved tasks: " + message);
    }
}
