package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.MarkCommand;
import darkpool.DarkpoolException;

import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validate;
import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validateNumber;


public class MarkParser {

    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new MarkCommand(validateNumber(userInput));
    }

}
