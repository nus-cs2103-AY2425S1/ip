package drbrown.parsing;

import drbrown.command.Command;
import drbrown.command.ListCommand;
import drbrown.utils.DrBrownException;
import drbrown.utils.Ui;

/**
 * A parser that handles the "list" command input.
 * Responsible for parsing the input to display the list of tasks.
 */
public class ListParser extends Parsing {

    public ListParser(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Parses the input to create a {@link ListCommand} that lists all tasks.
     *
     * @return An instance of {@link ListCommand} for listing all tasks.
     * @throws DrBrownException If the input contains more than one word.
     */
    public Command parse() throws DrBrownException {
        assert this.getInputSplit() != null : "Input string array should not be null";
        if (this.getInputSplit().length != 1) {
            throw new DrBrownException(Ui.getListException());
        }
        return new ListCommand();
    }

}
