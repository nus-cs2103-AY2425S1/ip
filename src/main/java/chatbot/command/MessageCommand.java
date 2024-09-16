package chatbot.command;

/**
 * Represents a command to the bot to print a message
 */
public class MessageCommand extends Command {
    /** The message to be printed */
    private String message;

    /**
     * Constructor for the MessageCommand object
     *
     * @param message The message to be printed
     */
    public MessageCommand(String message) {
        this.message = message;
    }

    /**
     * Execution of the command, returns the message to be printed
     *
     * @return The message to be printed
     */
    @Override
    public String run() {
        return this.message;
    }
}
