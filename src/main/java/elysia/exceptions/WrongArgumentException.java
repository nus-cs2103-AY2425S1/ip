package elysia.exceptions;

public class WrongArgumentException extends ElysiaException {

    private String details;

    /**
     * Constructs a new ElysiaException with the specified detail message.
     *
     * @param argumentType The type of argument to be added to the error message.
     */
    public WrongArgumentException(String argumentType) {
        super("Arguments are of the wrong type or wrong format. Argument type expected: " + argumentType);
        this.details = argumentType;
    }

    public String getDetails() {
        return details;
    }
}
