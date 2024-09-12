package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;

/**
 * The {@code ListCommand} class handles the user's command to list all tasks.
 * It extends the {@code Command} class and executes the command to display the task list.
 */
public class ListCommand extends Command {

    private Storage storage;
    private Ui ui;

    /**
     * Constructs a {@code ListCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public ListCommand(String command, Storage storage, Ui ui) {
        super(command);
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public String execute() throws IncorrectCommandException {
        return this.ui.returnList();
    }
}
