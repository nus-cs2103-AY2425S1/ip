package snowy.common;

import snowy.data.SnowyException;

public class InvalidCommand extends Command {
    private final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute() throws SnowyException {
        return new CommandResult(errorMessage);
    }
}
