package michael;

/**
 * Represents the command to exit the chatbot
 */
public class ByeCommand {
    public ByeCommand() {}

    /**
     * Returns the appropriate feedback to the user when the bye command
     * is used in the chatbot.
     *
     * @return Goodbye message to bid farewell to the user.
     */
    public String feedback() {
        return "Bye. Hope to see you again soon!";
    }
}
