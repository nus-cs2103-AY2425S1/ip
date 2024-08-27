package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;

public abstract class Command {
    public boolean isExit() { return false; }
    public abstract void execute(String userInput) throws AvoException;
}
