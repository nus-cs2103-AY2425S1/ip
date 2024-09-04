package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
/**
 * Represents a command
 */
public abstract class Command {
    public abstract CommandResult execute(String userInput) throws AvoException;
}
