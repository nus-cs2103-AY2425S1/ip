package greetbot;
public class EmptyDescriptionException extends Exception {
    
    final private String MESSAGE;

    public EmptyDescriptionException(String message) {
        super();
        this.MESSAGE = message;
    }

    public String getMessage() {
        return this.MESSAGE;
    }
}
