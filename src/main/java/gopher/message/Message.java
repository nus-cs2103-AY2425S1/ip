package gopher.message;

/**
 * Represents message returned by Gopher when user input any commands.
 * Contains information such as text, message type.
 */
public class Message {
    private String text;
    private MessageType type;

    /**
     * Constructor for Message class.
     *
     * @param text text within the message
     * @param type type of message, specified by MessageType Enum class
     */
    public Message(String text, MessageType type) {
        this.text = text;
        this.type = type;
    }

    /**
     * Gets the type of the Message Object
     *
     * @return type of message
     */
    public MessageType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return text;
    }
}
