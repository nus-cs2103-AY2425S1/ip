package nixy.command;

import nixy.ui.Ui;

/**
 * Class representing the command to exit the application.
 */
public class ByeCommand implements Command {
    private Ui ui;

    /**
     * Constructor for ByeCommand.
     *
     * @param ui The Ui object to interact with the user.
     */
    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    /**
     * Returns the type of command.
     *
     * @return CommandType.BYE
     */
    @Override
    public CommandType getType() {
        return CommandType.BYE;
    }

    /**
     * Executes the command print the goodbye message.
     */
    @Override
    public void execute() {
        // Do nothing
        ui.showGoodbye();
    }
}
