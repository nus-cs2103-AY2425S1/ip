package botty.exceptions;

/**
 * Exception thrown when a required argument is not found.
 */
public class ArgumentNotFoundException extends BottyException {
    /**
     * Constructs a new {@code ArgumentNotFoundException} with a detail message
     * indicating which argument was not found.
     *
     * @param argument the name of the argument that was not found, used to build the detail message.
     */
    public ArgumentNotFoundException(String argument) {
        super("The following argument is not found: " + argument);
    }
}
