package derek.command;

import derek.Storage;
import derek.Ui;
import derek.command.Command;

/**
 * The {@code LeavingCommand} class handles the user's command to exit the application.
 * It extends the {@code Command} class and executes the command to store tasks and display the exit message.
 */
public class LeavingCommand extends Command {

    /**
     * Constructs a {@code LeavingCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public LeavingCommand(String command) {
        super(command);
    }

    /**
     * Executes the command to store the tasks in the file and display the exit message.
     *
     * @param storage the storage object containing the task list
     * @param ui the UI object to interact with the user
     */
    public void execute(Storage storage, Ui ui) {
        storage.storeInFile();
        ui.printLeavingMessage();
    }
}
