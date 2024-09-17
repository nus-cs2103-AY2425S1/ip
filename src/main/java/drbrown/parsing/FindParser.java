package drbrown.parsing;

import drbrown.command.Command;
import drbrown.command.FindCommand;
import drbrown.utils.DrBrownException;
import drbrown.utils.Ui;

/**
 * A parser that handles the "find" command input.
 * Responsible for parsing the input to search for tasks containing a specific keyword.
 */
public class FindParser extends Parsing {

    public FindParser(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Parses the input to create a {@link FindCommand} that searches for tasks containing a specific keyword.
     *
     * @return An instance of {@link FindCommand} for finding tasks with the specified keyword.
     * @throws DrBrownException If the input is invalid, such as missing the keyword.
     */
    public Command parse() throws DrBrownException {
        assert this.getInputSplit() != null : "Input string array should not be null";
        if (this.getInputSplit().length == 1) {
            throw new DrBrownException(Ui.getFindException());
        }
        return new FindCommand(this.getInputSplit()[1]);
    }

}
