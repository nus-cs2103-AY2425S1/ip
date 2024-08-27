package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;

public class UnknownCommand extends Command {
    @Override
    public void execute(String userInput) throws AvoException {
        throw new AvoException("OOPS!!! I'm sorry, but I don't know what " + userInput + " means :-(");
    }
}
