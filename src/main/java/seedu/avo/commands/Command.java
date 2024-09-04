package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
/**
 * Represents a command
 */
public abstract class Command {
    public boolean isExit() {
        return false; }
    public abstract CommandResult execute(String userInput) throws AvoException;
}
