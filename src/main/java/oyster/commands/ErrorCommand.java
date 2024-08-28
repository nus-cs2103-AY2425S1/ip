package oyster.commands;

import oyster.Oyster;

public class ErrorCommand extends Command {
    public ErrorCommand(String message) {
        super(message);
    }

    @Override
    public void execute() {
        // do nothing
    }
}
