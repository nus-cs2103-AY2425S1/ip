package duck.command;

import duck.storage.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command that can be executed within the application.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface to interact with the user
     * @param storage the storage to save or load tasks
     */
    void executeCommand(TaskList tasks, Ui ui, Storage storage);
}
