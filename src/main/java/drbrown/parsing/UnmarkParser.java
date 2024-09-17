package drbrown.parsing;

import static java.lang.Integer.parseInt;

import drbrown.command.Command;
import drbrown.command.UnmarkCommand;
import drbrown.utils.DrBrownException;
import drbrown.utils.Ui;

/**
 * A parser that handles the "unmark" command input.
 * Responsible for parsing the input to unmark a task as not completed by its index.
 */
public class UnmarkParser extends Parsing {

    public UnmarkParser(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Parses the input to create an {@link UnmarkCommand} that marks a task as not completed.
     *
     * @return An instance of {@link UnmarkCommand} for unmarking the specified task as not completed.
     * @throws DrBrownException If the input is invalid, such as missing the index or providing a non-numeric value.
     */
    public Command parse() throws DrBrownException {
        assert this.getInputSplit() != null : "Input string array should not be null";
        try {
            if (this.getInputSplit().length == 1) {
                throw new DrBrownException(Ui.getUnmarkException());
            }
            int itemUnmarkIndex = parseInt(this.getInputSplit()[1]) - 1;
            return new UnmarkCommand(itemUnmarkIndex);
        } catch (NumberFormatException e) {
            throw new DrBrownException(Ui.getExceptionNotNumber());
        }
    }

}
