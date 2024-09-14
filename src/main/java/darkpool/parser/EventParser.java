package darkpool.parser;

import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.task.Event;
import darkpool.util.DarkpoolException;


public class EventParser {

    static public Command parse(String[] userInput) throws DarkpoolException {
        return new AddCommand(getEvent(userInput));
    }

    private static Event getEvent(String[] userInput) throws DarkpoolException {
        return checkCommand.eventChecker(userInput);
    }

}
