package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.MarkCommand;
import darkpool.util.DarkpoolException;


public class MarkParser {

    static public Command parse(String[] userInput) throws DarkpoolException {
        return new MarkCommand(taskAction(userInput));
    }

    private static int taskAction(String[] userInput) throws DarkpoolException {
        return checkCommand.markUnmarkDeleteChecker(userInput);
    }

}
