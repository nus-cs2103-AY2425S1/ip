package greetbot;

/**
 * A exception class that is thrown when a command has empty description.
 */
public class EmptyDescriptionException extends Exception {
    
    final private String MESSAGE;

    public EmptyDescriptionException(String message) {
        super();
        this.MESSAGE = message;
    }

    /**
     *  Returns a String that shows the command has empty description.
     * @return Description of the exception.
     */
    public String getMessage() {
        return this.MESSAGE;
    }
}
