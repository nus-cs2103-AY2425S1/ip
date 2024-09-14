package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.DeleteCommand;
import darkpool.util.DarkpoolException;


public class DeleteParser {

    static public Command parse(String[] userInput) throws DarkpoolException {
        return new DeleteCommand(taskAction(userInput));
    }

    private static int taskAction(String[] userInput) throws DarkpoolException {
        return checkCommand.markUnmarkDeleteChecker(userInput);
    }

}
