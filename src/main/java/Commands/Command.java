package Commands;

import Exceptions.AvoException;

public abstract class Command {
    public abstract void execute(String userInput) throws AvoException;
}
