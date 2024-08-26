package Commands;

import Exceptions.AvoException;

public abstract class Command {
    public boolean isExit() { return false; }
    public abstract void execute(String userInput) throws AvoException;
}
