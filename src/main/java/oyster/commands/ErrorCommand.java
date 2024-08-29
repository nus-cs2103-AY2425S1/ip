package oyster.commands;

import oyster.Oyster;

public class ErrorCommand extends Command {
    /**
     * Creates an ErrorCommand that holds a message.
     *
     * @param message The message to display in the ErrorCommand.
     */
    public ErrorCommand(String message) {
        super(message);
    }

    /**
     * Does not do anything when executed.
     */
    @Override
    public void execute() {
        // do nothing
    }
}
