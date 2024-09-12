package derek.command;

import derek.Storage;
import derek.Ui;
import derek.command.Command;
import derek.exception.IncorrectCommandException;

/**
 * The {@code LeavingCommand} class handles the user's command to exit the application.
 * It extends the {@code Command} class and executes the command to store tasks and display the exit message.
 */
public class LeavingCommand extends Command {

    private Storage storage;
    private Ui ui;

    /**
     * Constructs a {@code LeavingCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public LeavingCommand(String command, Storage storage, Ui ui) {
        super(command);
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public String execute() throws IncorrectCommandException {
        this.storage.storeInFile();
        return this.ui.printLeavingMessage();
    }
}
