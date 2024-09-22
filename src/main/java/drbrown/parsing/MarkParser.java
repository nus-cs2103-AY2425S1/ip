package drbrown.parsing;

import static java.lang.Integer.parseInt;

import drbrown.command.Command;
import drbrown.command.MarkCommand;
import drbrown.utils.DrBrownException;
import drbrown.utils.Ui;

/**
 * A parser that handles the "mark" command input.
 * Responsible for parsing the input to mark a task as completed by its index.
 */
public class MarkParser extends Parsing {

    public MarkParser(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Parses the input to create a {@link MarkCommand} that marks a task as completed.
     *
     * @return An instance of {@link MarkCommand} for marking the specified task as completed.
     * @throws DrBrownException If the input is invalid, such as missing the index or providing a non-numeric value.
     */
    public Command parse() throws DrBrownException {
        assert this.getInputSplit() != null : "Input string array should not be null";
        try {
            if (this.getInputSplit().length == 1) {
                throw new DrBrownException(Ui.getMarkException());
            }
            int itemMarkIndex = parseInt(this.getInputSplit()[1]) - 1;
            return new MarkCommand(itemMarkIndex);
        } catch (NumberFormatException e) {
            throw new DrBrownException(Ui.getExceptionNotNumber());
        }
    }

}
