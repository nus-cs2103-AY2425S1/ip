package elysia.exceptions;

public class ArgumentFormatException extends ElysiaException {

    private String details;

    /**
     * Constructs a new ElysiaException with the specified detail message.
     *
     * @param commandType The type of command to be added to the error message.
     */
    public ArgumentFormatException(String commandType) {
        super("Incorrect number of arguments or arguments formatted wrongly for " + commandType);
        this.details = commandType;
    }

    public String getDetails() {
        return details;
    }
}
