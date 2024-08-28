package evan.exception;

public class SavingException extends Exception {
    public SavingException(String message) {
        super("Oh no! There was an error when saving the tasks: " + message);
    }
}
