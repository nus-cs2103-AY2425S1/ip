package drbrown.parsing;

import drbrown.command.Command;
import drbrown.command.ExitCommand;
import drbrown.utils.DrBrownException;

/**
 * A parser that handles the "bye" command input.
 * Responsible for parsing the input to determine if it matches the expected format
 * for exiting the application.
 */
public class ByeParser {

    /**
     * Parses the input to create a command that exits the application.
     *
     * @param inputSplit An array of strings containing the user's input split by spaces.
     * @return An instance of {@link ExitCommand} if the input is valid.
     * @throws DrBrownException If the input contains more than one word.
     */
    public static Command parse(String[] inputSplit) throws DrBrownException {
        if (inputSplit.length != 1) {
            throw new DrBrownException("Whoa, hold on! You've written more letters than necessary! "
                    + "It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!");
        }
        return new ExitCommand();
    }

}
