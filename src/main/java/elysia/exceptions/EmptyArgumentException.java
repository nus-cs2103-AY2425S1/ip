package elysia.exceptions;

/**
 * Custom exception class to handle cases where a required argument is missing for a specific command
 * in the Elysia application. Extends the {@code ElysiaException} class.
 */
public class EmptyArgumentException extends ElysiaException {

    private String details;

    /**
     * Constructs a new {@code EmptyArgumentException} with a specified command.
     * The exception message indicates that no argument was found for the command, and exactly one argument is expected.
     *
     * @param command the command for which the argument is missing.
     */
    public EmptyArgumentException(String command) {
        super("No argument found for " + command + " command. 1 expected");
        this.details = command;
    }

    /**
     * Returns the details about the command that triggered the exception due to a missing argument.
     *
     * @return a string representing the command that lacked the expected argument.
     */
    public String getDetails() {
        return details;
    }
}
