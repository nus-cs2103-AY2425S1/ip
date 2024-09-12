package sage.Command;

import sage.List.TaskList;
import sage.Storage;
import sage.Ui;

import java.io.IOException;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Executes the exit command. Saves the current state of the task list to storage and displays a goodbye message.
     * If an IOException occurs during saving, an error message is shown.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object to handle user interaction.
     * @param storage The Storage object for saving changes to the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTasks(tasks);
            return ui.showGoodbye();
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
    }
}
