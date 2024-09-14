package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.UnmarkCommand;
import darkpool.DarkpoolException;


public class UnmarkParser {

    static public Command parse(String[] userInput) throws DarkpoolException {
        return new UnmarkCommand(taskAction(userInput));
    }

    private static int taskAction(String[] userInput) throws DarkpoolException {
        return checkCommand.markUnmarkDeleteChecker(userInput);
    }

}
