package duke;

/**
 * This exception is thrown whenever a command does not receive any further required arguments.
 */
public class EmptyNonTaskCommandException extends RuntimeException {
    @Override
    public String getMessage() {
        return "The command entered requires more details. Perhaps you forgot to choose the index of the item you"
                + " wanted to act upon?";
    }
}
