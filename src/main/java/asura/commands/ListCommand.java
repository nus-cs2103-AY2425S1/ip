package asura.commands;

import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

/**
 * Represents a user inputting a ListCommand.
 */
public class ListCommand extends Command {

    public ListCommand() {
    }

    /**
     * Outputs the list of tasks of the user.
     * @param tasklist The list of tasks of the user.
     * @param ui The UI object to give user feedback.
     * @param storage The storage object to save/load tasks.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        output.append("Here are the tasks in your list:\n");
        output.append(tasklist.toString());
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
