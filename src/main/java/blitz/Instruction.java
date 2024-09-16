package blitz;

import java.util.Arrays;

/**
 * Represents the various commands that the Blitz application can process.
 */
public enum Instruction {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;

    /**
     * Checks if the given command exist as an enum constant.
     *
     * @param command Command String to check for existence.
     * @return True if the command matches any of the enum constant, false otherwise.
     */
    private static boolean contains(String command) {
        return Arrays.stream(Instruction.values())
                .anyMatch(cmd -> cmd.name().equalsIgnoreCase(command.toUpperCase()));
    }

    /**
     * Checks if the specified command exist.
     *
     * @param command Command String to be checked.
     * @return True if the command exists, false otherwise.
     */
    public static boolean isValidCommand(String command) {
        return Instruction.contains(command);
    }
}
