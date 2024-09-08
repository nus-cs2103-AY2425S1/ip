package drbrown.parsing;

import static java.lang.Integer.parseInt;

import drbrown.command.Command;
import drbrown.command.DeleteCommand;
import drbrown.utils.DrBrownException;

/**
 * A parser that handles the "delete" command input.
 * Responsible for parsing the input to delete a task by its index.
 */
public class DeleteParser {

    /**
     * Parses the input to create a {@link DeleteCommand} that deletes a task at the specified index.
     *
     * @param inputSplit An array of strings containing the user's input split by spaces.
     * @return An instance of {@link DeleteCommand} for deleting the specified task.
     * @throws DrBrownException If the input is invalid, such as missing the index or providing a non-numeric value.
     */
    public static Command parse(String[] inputSplit) throws DrBrownException {
        try {
            if (inputSplit.length == 1) {
                throw new DrBrownException("You can't erase something from history without a count!\n"
                        + "Use the format: delete {count}");
            }
            int itemDeleteIndex = parseInt(inputSplit[1]) - 1;
            return new DeleteCommand(itemDeleteIndex);
        } catch (NumberFormatException e) {
            throw new DrBrownException("That's not a number! Without the right input, we're never going to get "
                    + "this DeLorean off the ground!");
        }
    }

}
