package lexi.command;

import lexi.storage.Storage;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents the command to exit the Lexi application.
 * When executed, this command sets the exit status and displays a goodbye message to the user.
 */
public class ByeCommand extends Command {

    private String response;

    /**
     * Executes the bye command.
     * This method sets the application to exit and displays a goodbye message to the user.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The UI object to interact with the user.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        response = ui.showBye();
    }

    /**
     * Returns the name of the command.
     *
     * @return The string "BYE".
     */
    @Override
    public String getCommandName() {
        return "BYE";
    }

    @Override
    public String getString() {
        return response;
    }
}
