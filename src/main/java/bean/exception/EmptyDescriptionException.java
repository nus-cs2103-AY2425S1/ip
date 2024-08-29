package bean.exception;

public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException(String message) {
        super(message);
    }
    public EmptyDescriptionException() {
        super("The description cannot be empty! Please enter a task description :)");
    }
}
