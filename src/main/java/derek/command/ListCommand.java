package derek.command;

import derek.Storage;
import derek.Ui;

/**
 * The {@code ListCommand} class handles the user's command to list all tasks.
 * It extends the {@code Command} class and executes the command to display the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the command to display the current task list.
     *
     * @param storage the storage object containing the task list
     * @param ui the UI object to interact with the user
     */
    public void execute(Storage storage, Ui ui) {
        ui.returnList();
    }
}
