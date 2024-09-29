package chatbot.exception;

/** Exception to be thrown when Empty Description for Task is encountered */
public class EmptyDescException extends Exception {
    public EmptyDescException() {
        super("Task Description cannot be empty");
    }
}
