package meep.parser;

/**
 * The {@code CommandParser} class provides utility methods to parse and validate user commands.
 * It offers functionality to check if a command in the input string matches the expected command,
 * either with or without additional arguments.
 */
public class CommandParser {

    /**
     * Checks if the given input string starts with the specified command.
     * This method is typically used for commands that require additional arguments.
     *
     * @param input   The full input string provided by the user.
     * @param command The command to check against.
     * @return {@code true} if the input starts with the specified command (case-insensitive), {@code false} otherwise.
     */
    public static boolean checkCommandWithArgument(String input, String command) {
        return input.split(" ")[0].equalsIgnoreCase(command);
    }

    /**
     * Checks if the given input string exactly matches the specified command.
     * This method is typically used for commands that do not require additional arguments.
     *
     * @param input   The full input string provided by the user.
     * @param command The command to check against.
     * @return {@code true} if the input exactly matches the specified command
     *     (case-insensitive),{@code false} otherwise.
     */
    public static boolean checkCommandWithoutArgument(String input, String command) {
        return input.equalsIgnoreCase(command);
    }

    /**
     * Checks if the given input string contains illegal characters.
     *
     * @param input The full input string provided by the user.
     * @return {@code true} if the input contains illegal characters, {@code false} otherwise.
     */
    public static boolean checkIllegalCharacters(String input) {
        // "|" is used as a delimiter in the save format of tasks
        return input.contains("|");
    }
}
