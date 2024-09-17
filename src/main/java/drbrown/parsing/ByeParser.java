package drbrown.parsing;

import drbrown.command.Command;
import drbrown.command.ExitCommand;
import drbrown.utils.DrBrownException;

/**
 * A parser that handles the "bye" command input.
 * Responsible for parsing the input to determine if it matches the expected format
 * for exiting the application.
 */
public class ByeParser extends Parsing {

    public ByeParser(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Parses the input to create a command that exits the application.
     *
     * @return An instance of {@link ExitCommand} if the input is valid.
     * @throws DrBrownException If the input contains more than one word.
     */
    @Override
    public Command parse() throws DrBrownException {
        assert this.getInputSplit() != null : "Input string array should not be null";
        if (this.getInputSplit().length != 1) {
            throw new DrBrownException("Whoa, hold on! You've written more letters than necessary! "
                    + "It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!");
        }
        return new ExitCommand();
    }

}
