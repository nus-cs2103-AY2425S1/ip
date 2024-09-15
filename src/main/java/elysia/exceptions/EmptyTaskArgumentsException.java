package elysia.exceptions;

public class EmptyTaskArgumentsException extends ElysiaException {

    private String details;

    /**
     * Constructs a new ElysiaException with the specified detail message.
     *
     * @param eventType The type of event to be added to the error message.
     */
    public EmptyTaskArgumentsException(String eventType) {
        super("No arguments found for the task command.");
        this.details = eventType;
    }

    public String getDetails() {
        return details;
    }
}
