package Commands;

import Exceptions.AvoException;

public class UnknownCommand extends Command {
    @Override
    public void execute(String userInput) throws AvoException {
        throw new AvoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
