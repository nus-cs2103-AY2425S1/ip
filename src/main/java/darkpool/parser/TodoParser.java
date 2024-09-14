package darkpool.parser;

import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.DarkpoolException;


public class TodoParser {

    static public Command parse(String[] userInput) throws DarkpoolException {
        return new AddCommand(checkCommand.todoChecker(userInput));
    }

}
