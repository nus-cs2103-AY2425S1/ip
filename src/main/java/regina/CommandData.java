package regina;

/**
 * Represents a command issued by the user in the Regina chatbot.
 * This class encapsulates the command type and the corresponding raw input string.
 */
public class CommandData {
    private final String commandType; // The type of command (e.g. "add", "delete", etc.)
    private final String rawInput; // The original input string from the user

    /**
     * Constructs a CommandData instance with the specified command type and raw input.
     *
     * @param commandType A string representing the type of command issued by the user.
     * @param rawInput    The raw input string from the user, which may include additional details.
     */
    public CommandData(String commandType, String rawInput) {
        assert commandType != null && !commandType.isEmpty() : "Command type should not be null or empty";
        assert rawInput != null && !rawInput.isEmpty() : "Raw input should not be null or empty";
        this.commandType = commandType;
        this.rawInput = rawInput;
    }

    /**
     * Retrieves the command type of this CommandData.
     *
     * @return A string representing the command type.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Retrieves the raw input associated with this CommandData.
     *
     * @return A string containing the original input provided by the user.
     */
    public String getRawInput() {
        return rawInput;
    }
}
