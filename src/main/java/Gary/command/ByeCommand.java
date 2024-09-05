package Gary.command;

import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;

/**
 * The {@code ByeCommand} class represents a command to exit the task management application.
 * It extends the {@code Command} class and implements the behavior for exiting the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command, which displays a goodbye message and ends the application.
     *
     * @param tasks The {@code TaskList} object containing tasks to be manipulated (not used in this command).
     * @param ui The {@code Ui} object for user interaction, used to display the goodbye message.
     * @param storage The {@code Storage} object for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
    }

    /**
     * Indicates that this command is a "bye" command.
     *
     * @return {@code true} as this is a "bye" command.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
