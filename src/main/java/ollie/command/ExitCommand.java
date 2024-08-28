package ollie.command;

import ollie.exception.OllieException;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;

/**
 * Represents a command for ending the conversation.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Execute the command to save the data and exit the conversation.
     *
     * @param tasks List of tasks.
     * @param ui User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        // Save data
        storage.save(tasks.getTasks());
        ui.showExit();
    };
}
