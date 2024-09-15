package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.DeleteCommand;
import darkpool.DarkpoolException;

import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validate;
import static darkpool.parser.validator.ValidateMarkUnmarkDelete.validateNumber;


public class DeleteParser {

    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new DeleteCommand(validateNumber(userInput));
    }

}
