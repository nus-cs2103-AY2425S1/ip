package darkpool.parser;

import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validate;
import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validateNumber;

import darkpool.DarkpoolException;
import darkpool.command.Command;
import darkpool.command.UnmarkCommand;

/**
 * UnmarkParser class is responsible for parsing the user input for unmark command.
 */
public class UnmarkParser {

    /**
     * Parses the user input for unmark command.
     *
     * @param userInput The user input to be parsed.
     * @return The UnmarkCommand object.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new UnmarkCommand(validateNumber(userInput));
    }

}
