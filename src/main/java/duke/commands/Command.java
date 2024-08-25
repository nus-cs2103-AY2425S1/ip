package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an abstract command in the DailyTasks application.
 * Commands are responsible for performing specific actions that manipulate
 * the task list, interact with the user interface, or modify the storage.
 */
public abstract class Command {

    /**
     * Executes the command using the provided task list, user interface, and storage.
     * Subclasses of Command should override this method to provide specific behavior.
     *
     * @param taskList The list of tasks to be manipulated by the command.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to read from or write to the file system.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
