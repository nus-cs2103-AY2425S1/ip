package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

/**
 * Represents a user inputting a MarkCommand.
 */
public class MarkCommand extends Command {
    int selection;

    /**
     * Creates a MarkCommand with the specified selection.
     * @param selection The index of the task that the user wants to mark as done.
     */
    public MarkCommand(int selection) {
        this.selection = selection;
    }

    /**
     * Marks a task as done on the index specified by the user.
     * @param tasklist The list of tasks of the user.
     * @param ui The UI object to give user feedback.
     * @param storage The storage object to save/load tasks.
     * @throws AsuraException If saving user data fails.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws AsuraException {
        tasklist.mark(selection);
        storage.save(tasklist.getTaskList());
        output.append("Nice! I've marked this task as done:").append("\n").append(tasklist.get(selection).toString());
        ui.printString(output.toString());
    }

    /**
     * Indicates that the user does not want to terminate the program.
     * @return A boolean representing whether the program is to be terminated, in this case false.
     */
    public boolean isExit() {
        return false;
    }
}
