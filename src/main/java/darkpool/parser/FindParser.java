package darkpool.parser;

import static darkpool.parser.validator.ValidateFind.validate;

import darkpool.DarkpoolException;
import darkpool.command.Command;
import darkpool.command.FindCommand;

/**
 * FindParser class is responsible for parsing the user input for the find command.
 */
public class FindParser {

    /**
     * Parses the user input for the find command.
     *
     * @param userInput The user input to be parsed.
     * @return The FindCommand object.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new FindCommand(userInput[1]);
    }

}
