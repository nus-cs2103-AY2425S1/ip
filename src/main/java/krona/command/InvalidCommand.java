package krona.command;

import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;

/**
 * Represents a command that is invalid or not recognized by the Krona chatbot.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the command by displaying an error message indicating that the command is not recognized.
     *
     * @param tasks   The task list that the command operates on (not used in this command).
     * @param ui      The UI component that handles interactions with the user.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}