package elysia.exceptions;

/**
 * Custom exception class to handle errors related to incorrect argument formatting
 * or an incorrect number of arguments in the Elysia application.
 * Extends the {@code ElysiaException} class.
 */
public class ArgumentFormatException extends ElysiaException {

    private String details;

    /**
     * Constructs a new {@code ArgumentFormatException} with a specified command type.
     * The exception message indicates that the arguments are either wrongly formatted
     * or the number of arguments is incorrect for the given command.
     *
     * @param commandType the type of command for which the arguments are incorrect or misformatted.
     */
    public ArgumentFormatException(String commandType) {
        super("Incorrect number of arguments or arguments formatted wrongly for " + commandType);
        this.details = commandType;
    }

    /**
     * Returns the details about the command type that triggered the exception.
     *
     * @return a string representing the command type.
     */
    public String getDetails() {
        return details;
    }
}
