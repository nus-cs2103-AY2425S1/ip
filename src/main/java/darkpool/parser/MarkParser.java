package darkpool.parser;

import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validate;
import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validateNumber;

import darkpool.DarkpoolException;
import darkpool.command.Command;
import darkpool.command.MarkCommand;

/**
 * Parses the user input for marking a task as done.
 */
public class MarkParser {

    /**
     * Parses the user input for marking a task as done.
     *
     * @param userInput The user input.
     * @return The MarkCommand object.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new MarkCommand(validateNumber(userInput));
    }

}
