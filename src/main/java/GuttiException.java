/**
 * The {@code GuttiException} class represents a custom exception used in the {@code Gutti} chatbot.
 * <p>
 * This exception is thrown to indicate errors specific to the chatbot's operations,
 * such as incorrect command formats or invalid task operations.
 * </p>
 */
public class GuttiException extends Exception{
    /**
     * Constructs a new {@code GuttiException} with the specified detail message.
     * <p>
     * The detail message is saved for later retrieval by the {@link #getMessage()} method.
     * </p>
     *
     * @param message The detail message to be saved for this exception.
     */
    public GuttiException(String message) {
        super(message);
    }
}
