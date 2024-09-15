package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.FindCommand;
import darkpool.DarkpoolException;

import static darkpool.parser.validator.ValidateFind.validate;

public class FindParser {

    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new FindCommand(userInput[1]);
    }

}
