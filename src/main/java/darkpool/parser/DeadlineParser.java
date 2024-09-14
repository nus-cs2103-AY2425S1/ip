package darkpool.parser;

import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.task.Deadline;
import darkpool.DarkpoolException;


public class DeadlineParser {

    static public Command parse(String[] userInput) throws DarkpoolException {
        return new AddCommand(getDeadline(userInput));
    }

    private static Deadline getDeadline(String[] userInput) throws DarkpoolException {
        return checkCommand.deadlineChecker(userInput);
    }

}
