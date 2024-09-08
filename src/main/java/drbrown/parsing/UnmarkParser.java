package drbrown.parsing;

import static java.lang.Integer.parseInt;

import drbrown.command.Command;
import drbrown.command.UnmarkCommand;
import drbrown.utils.DrBrownException;

/**
 * A parser that handles the "unmark" command input.
 * Responsible for parsing the input to unmark a task as not completed by its index.
 */
public class UnmarkParser {

    /**
     * Parses the input to create an {@link UnmarkCommand} that marks a task as not completed.
     *
     * @param inputSplit An array of strings containing the user's input split by spaces.
     * @return An instance of {@link UnmarkCommand} for unmarking the specified task as not completed.
     * @throws DrBrownException If the input is invalid, such as missing the index or providing a non-numeric value.
     */
    public static Command parse(String[] inputSplit) throws DrBrownException {
        try {
            if (inputSplit.length == 1) {
                throw new DrBrownException("Great Scott! You can't go back in time without a count!\n"
                        + "Use the format: unmark {count}");
            }
            int itemUnmarkIndex = parseInt(inputSplit[1]) - 1;
            return new UnmarkCommand(itemUnmarkIndex);
        } catch (NumberFormatException e) {
            throw new DrBrownException("That's not a number! Without the right input, we're never going to get "
                    + "this DeLorean off the ground!");
        }
    }

}
