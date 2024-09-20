package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;

/**
 * Represents the command to exit the app
 */
public class ExitCommand extends Command {
    private static final ExitCommand INSTANCE = new ExitCommand();
    private ExitCommand() {}
    public static ExitCommand of() {
        return INSTANCE;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        return new CommandResult("", true);
    }
}
