package darkpool.parser;

import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validate;
import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validateNumber;

import darkpool.DarkpoolException;
import darkpool.command.Command;
import darkpool.command.DeleteCommand;

/**
 * DeleteParser class is responsible for parsing the user input for delete command.
 */
public class DeleteParser {

    /**
     * Parses the user input for delete command.
     *
     * @param userInput User input for delete command.
     * @return Command object for delete command.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new DeleteCommand(validateNumber(userInput));
    }

}
