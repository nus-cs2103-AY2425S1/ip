package Niko.Command;

import Niko.Common.NikoException;
import Niko.Main.Storage;
import Niko.Main.Ui;
import Niko.Task.TaskList;

/**
 * Represents an abstract Command that can be executed with a task list, UI, and storage.
 * Subclasses should implement the {@link #execute(TaskList, Ui, Storage)} method to define
 * specific behavior for different commands.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save or load tasks.
     * @throws NikoException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NikoException;

    /**
     * Indicates whether this command will exit the application.
     *
     * @return {@code true} if this command exits the application; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
