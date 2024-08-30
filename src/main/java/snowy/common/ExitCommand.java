package snowy.common;

import snowy.data.SnowyException;

public class ExitCommand extends Command {
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "See you next time. Goodbye!";

    public ExitCommand() {
        super();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    /**
     * Returns true if the command is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
