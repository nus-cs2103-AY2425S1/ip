package mortalreminder.commands;

/**
 * Represents a command input by the user, encapsulating the command type and any associated details.
 *
 * @param commandType   The type of command provided by the user.
 * @param commandDetails An array of strings containing the details associated with the command.
 */
public record Command(CommandTypes commandType, String[] commandDetails) {
    /**
     * Creates a new {@code Command} instance with the specified command type and details.
     * This method serves as a factory method for creating a {@code Command} object.
     *
     * @param commandType The type of command to be executed, as specified by the {@link CommandTypes} enum.
     * @param input       An array of strings containing the details associated with the command.
     * @return A new {@code Command} object initialized with the given command type and details.
     */
    // Initialise method
    public static Command initialise(CommandTypes commandType, String[] input) {
        // Return a new Commands object with commandType and commandDetails
        return new Command(commandType, input);
    }
}
