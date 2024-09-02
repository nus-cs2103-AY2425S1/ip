package commands;

import main.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * The {@code Command} class represents an abstract command that can be executed.
 * Subclasses of {@code Command} will provide specific implementations for different command types.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param taskList the list of tasks to operate on
     * @param ui the user interface to interact with the user
     * @param storage the storage handler for loading and saving tasks
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}