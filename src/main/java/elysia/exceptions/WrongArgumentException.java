package elysia.exceptions;

/**
 * Custom exception class to handle cases where arguments provided are of the wrong type or format
 * in the Elysia application. Extends the {@code ElysiaException} class.
 */
public class WrongArgumentException extends ElysiaException {

    private String details;

    /**
     * Constructs a new {@code WrongArgumentException} with a specified expected argument type.
     * The exception message indicates that the arguments provided are of the wrong type or format.
     *
     * @param argumentType the expected type or format of the arguments.
     */
    public WrongArgumentException(String argumentType) {
        super("Arguments are of the wrong type or wrong format. Argument type expected: " + argumentType);
        this.details = argumentType;
    }

    /**
     * Returns the details about the expected argument type that triggered the exception.
     *
     * @return a string representing the expected argument type or format.
     */
    public String getDetails() {
        return details;
    }
}
