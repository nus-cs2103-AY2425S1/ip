package oyster.commands;

import oyster.Oyster;

public class ErrorCommand extends Command {
    public ErrorCommand(String message) {
        super(message);
    }

    /**
     * Does not do anything when executed
     */
    @Override
    public void execute() {
        // do nothing
    }
}
