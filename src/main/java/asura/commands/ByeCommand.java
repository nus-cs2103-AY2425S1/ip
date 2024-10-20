package asura.commands;

import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

/**
 * Represents a command to terminate the program by the user.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
    }

    /**
     * Returns a farewell message without modifying tasks.
     * @param tasklist The list of tasks of the user.
     * @param ui The UI object to give user feedback.
     * @param storage The storage object to save/load tasks.
     * @return A farewell message to the user.
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return "Bye!";
    }

    /**
     * Indicates that the user wants to terminate the program.
     * @return A boolean representing whether the program is to be terminated, in this case true.
     */
    public boolean isExit() {
        return true;
    }
}
