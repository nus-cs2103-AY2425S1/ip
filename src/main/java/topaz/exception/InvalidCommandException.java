package topaz.exception;

/**
 * Represents an exception that is thrown when an invalid command is encountered.
 * This exception provides a custom message that includes the invalid command input.
 */
public class InvalidCommandException extends Exception {

    private String input;
    /**
     * Constructs an InvalidCommandException with the specified invalid command input.
     *
     * @param input The invalid command input that caused the exception.
     */
    public InvalidCommandException(String input) {
        super();
        this.input = input;
    }

    @Override
    public String toString() {
        return "Steady... Your put an invalid command: \""
                + input + "\", type \"help\" to see how to use the chatbot.";
    }
}
