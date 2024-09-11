package ipman.models;

/**
 * Thrown when serializing or deserializing a <code>Task</code> fails
 *
 * @see Task
 */
public class SerializationException extends RuntimeException {
    public SerializationException(String message) {
        super(message);
    }
}
