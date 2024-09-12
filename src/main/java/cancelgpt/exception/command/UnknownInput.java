package cancelgpt.exception.command;

/**
 * Represents an Exception for unknown inputs.
 */
public class UnknownInput extends Exception {
    /**
     * Initialises UnknownInput with a message indicating unknown input.
     */
    public UnknownInput() {
        super("Unknown input. Please enter an appropriate command that is known.");
    }
}
