package drbrown.parsing;

import static java.lang.Integer.parseInt;

import drbrown.command.Command;
import drbrown.command.MarkCommand;
import drbrown.utils.DrBrownException;

/**
 * A parser that handles the "mark" command input.
 * Responsible for parsing the input to mark a task as completed by its index.
 */
public class MarkParser {

    /**
     * Parses the input to create a {@link MarkCommand} that marks a task as completed.
     *
     * @param inputSplit An array of strings containing the user's input split by spaces.
     * @return An instance of {@link MarkCommand} for marking the specified task as completed.
     * @throws DrBrownException If the input is invalid, such as missing the index or providing a non-numeric value.
     */
    public static Command parse(String[] inputSplit) throws DrBrownException {
        assert inputSplit != null : "Input string array should not be null";
        try {
            if (inputSplit.length == 1) {
                throw new DrBrownException("Great Scott! You can't complete a task without a count!\n"
                        + "Use the format: mark {count}");
            }
            int itemMarkIndex = parseInt(inputSplit[1]) - 1;
            return new MarkCommand(itemMarkIndex);
        } catch (NumberFormatException e) {
            throw new DrBrownException("That's not a number! Without the right input, "
                    + "we're never going to get this DeLorean off the ground!");
        }
    }

}
