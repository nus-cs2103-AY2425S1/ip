package drbrown.parsing;

import static java.lang.Integer.parseInt;

import drbrown.command.Command;
import drbrown.command.DeleteCommand;
import drbrown.utils.DrBrownException;
import drbrown.utils.Ui;

/**
 * A parser that handles the "delete" command input.
 * Responsible for parsing the input to delete a task by its index.
 */
public class DeleteParser extends Parsing {

    public DeleteParser(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Parses the input to create a {@link DeleteCommand} that deletes a task at the specified index.
     *
     * @return An instance of {@link DeleteCommand} for deleting the specified task.
     * @throws DrBrownException If the input is invalid, such as missing the index or providing a non-numeric value.
     */
    public Command parse() throws DrBrownException {
        assert this.getInputSplit() != null : "Input string array should not be null";
        try {
            if (this.getInputSplit().length == 1) {
                throw new DrBrownException(Ui.getDeleteExceptionNoIndex());
            }
            int itemDeleteIndex = parseInt(this.getInputSplit()[1]) - 1;
            return new DeleteCommand(itemDeleteIndex);
        } catch (NumberFormatException e) {
            throw new DrBrownException(Ui.getExceptionNotNumber());
        }
    }

}
