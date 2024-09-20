package command;

import java.io.IOException;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to archive the task list.
 */
public class ArchiveCommand extends Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save the tasks.
     * @return The response to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.archive(tasks);
            return "Task list has been archived successfully.";
        } catch (IOException e) {
            return "Failed to archive the task list.";
        }
    }

    /**
     * Returns true if the command is an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
