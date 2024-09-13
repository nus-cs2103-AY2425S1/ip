package arts.util;

import arts.ArtsException;
import arts.enums.CommandType;

/**
 * The Parser class is responsible for interpreting user input and
 * converting it into executable commands and arguments.
 */
public class Parser {

    /**
     * Parses the command from the user input and determines the corresponding CommandType.
     *
     * @param input The user input string containing the command.
     * @return The CommandType corresponding to the command word in the input.
     * @throws ArtsException If the command word is not recognized.
     */
    public CommandType parseCommand(String input) throws ArtsException {
        assert input != null : "Input cannot be null";
        String trimmedInput = input.trim().toLowerCase();

        // Check for specific multi-word commands first
        if (trimmedInput.equals("sort deadlines")) {
            return CommandType.SORT_DEADLINES;
        } else if (trimmedInput.equals("sort events")) {
            return CommandType.SORT_EVENTS;
        }

        // Split the input by space to get the first word
        String[] parts = trimmedInput.split(" ", 2);
        assert parts.length > 0 : "Input must contain at least one word";
        String commandWord = parts[0].toUpperCase();

        try {
            return CommandType.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new ArtsException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses the arguments from the user input, separating the command from its parameters.
     *
     * @param input The user input string containing the command and arguments.
     * @return An array where the first element is the command and the second is the arguments.
     */
    public String[] parseArguments(String input) {
        assert input != null : "Input cannot be null";
        return input.trim().split(" ", 2);
    }
}
