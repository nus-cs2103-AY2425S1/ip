package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.FindCommand;
import darkpool.util.DarkpoolException;

public class FindParser {

    static public Command parse(String[] userInput) throws DarkpoolException {
        checkCommand.findChecker(userInput);
        return new FindCommand(userInput[1]);
    }

}
