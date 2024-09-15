package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.UnmarkCommand;
import darkpool.DarkpoolException;

import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validate;
import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validateNumber;


public class UnmarkParser {

    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new UnmarkCommand(validateNumber(userInput));
    }

}
