package command;
import fridayException.FridayException;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {

    /**
     * Executes the command with the specified task list, CLI, and storage to display messages to the user on the CLI.
     *
     * @param tasks The task list to be modified by the command.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @throws FridayException If an error occurs during execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException;

    /**
     * Executes the command with the specified task list, GUI, and storage to display messages to the user on the GUI.
     *
     * @param tasks The task list to be modified by the command.
     * @param gui The GUI to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @return The result of the command execution.
     * @throws FridayException If an error occurs during execution of the command.
     */
    public abstract String executeGui(TaskList tasks, UiGui gui, Storage storage) throws FridayException;

    /**
     * Indicates whether the command ends the scanner loop.
     *
     * @return false by default, indicating the scanner loop should continue.
     */
    public boolean isEndScanner() {
        return false;
    }
}
