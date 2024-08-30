package krona.command;

import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;

/**
 * Represents a command to exit the Krona chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by displaying a goodbye message to the user.
     *
     * @param tasks   The task list that the command operates on (not used in this command).
     * @param ui      The UI component that handles interactions with the user.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}