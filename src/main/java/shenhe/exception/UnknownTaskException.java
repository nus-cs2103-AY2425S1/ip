package shenhe.exception;

/**
 * Represents an exception that is thrown when an unrecognized or unsupported task command is encountered.
 * <p>
 * The {@code UnknownTaskException} class indicates that the user has entered a task command that the
 * application does not understand or support. This exception provides a specific error message to prompt
 * the user to provide a valid task type along with its description.
 * </p>
 */
public class UnknownTaskException extends Exception {

    /**
     * Constructs an {@code UnknownTaskException} with a default error message.
     * <p>
     * The default error message is: "Sorry traveller. I am not really sure I get what you mean.
     * Please give me the type of the task and its description."
     * </p>
     */
    public UnknownTaskException() {
        super("Sorry traveller. I am not really sure I get what you mean. Please give me the type of the task and " +
                "its description");
    }
}
