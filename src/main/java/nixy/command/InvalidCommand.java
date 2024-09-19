package nixy.command;

import nixy.ui.Ui;

/**
 * Class representing all invalid commands input.
 */
public class InvalidCommand implements Command {
    private Ui ui;

    /**
     * Constructor for InvalidCommand.
     *
     * @param ui The Ui object to interact with the user.
     */
    public InvalidCommand(Ui ui) {
        this.ui = ui;
    }

    /**
     * Returns the type of command.
     *
     * @return CommandType.INVALID
     */
    @Override
    public CommandType getType() {
        return CommandType.INVALID;
    }

    /**
     * Executes the command to print the invalid command message.
     */
    @Override
    public void execute() {
        ui.showUnknownWarning();
    }
}
