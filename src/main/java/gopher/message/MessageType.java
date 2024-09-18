package gopher.message;

/**
 * Types of messages that Gopher would respond to the user
 * @see #TEXT
 * @see #ERROR
 */
public enum MessageType {
    /**
     * Normal text message
     */
    TEXT,
    /**
     * Error message
     */
    ERROR
}
