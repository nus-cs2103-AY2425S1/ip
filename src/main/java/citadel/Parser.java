package citadel;

import citadel.exception.CitadelException;
import citadel.commands.Commands;
import citadel.exception.CitadelInvalidCommandException;

/**
 * The {@code Parser} class is responsible for parsing user input into commands
 * that can be executed by the Citadel application.
 */
public class Parser {

    /**
     * Parses the user's input to determine the command to be executed.
     * <p>
     * This method extracts the first word from the input string, converts it to uppercase,
     * and attempts to match it with a value from the {@link Commands} enum. If the input
     * does not match any valid command, a {@link CitadelInvalidCommandException} is thrown.
     * </p>
     *
     * @param input The user input string to be parsed.
     * @return The corresponding {@link Commands} enum value.
     * @throws CitadelException If the input does not match any valid command.
     */
    public static Commands parseCommand(String input) throws CitadelException {
        try {
            String command = input.split(" ")[0].toUpperCase();
            return Commands.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CitadelInvalidCommandException();
        }
    }
}
