package drbrown.parsing;

import drbrown.command.Command;
import drbrown.command.ListCommand;
import drbrown.utils.DrBrownException;

/**
 * A parser that handles the "list" command input.
 * Responsible for parsing the input to display the list of tasks.
 */
public class ListParser {

    /**
     * Parses the input to create a {@link ListCommand} that lists all tasks.
     *
     * @param inputSplit An array of strings containing the user's input split by spaces.
     * @return An instance of {@link ListCommand} for listing all tasks.
     * @throws DrBrownException If the input contains more than one word.
     */
    public static Command parse(String[] inputSplit) throws DrBrownException {
        assert inputSplit != null : "Input string array should not be null";
        if (inputSplit.length != 1) {
            throw new DrBrownException("Whoa, hold on! You've written more letters than necessary! "
                    + "It's like trying to fit a flux capacitor into a toaster - it just doesn't belong!");
        }
        return new ListCommand();
    }

}
