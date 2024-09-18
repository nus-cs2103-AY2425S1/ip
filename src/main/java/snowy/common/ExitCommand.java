package snowy.common;

import snowy.data.SnowyException;

public class ExitCommand extends Command {
    public static final String EXIT_MESSAGE = "See you next time. Goodbye!";

    public ExitCommand() {
        super();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
