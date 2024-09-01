package asura.commands;

import java.util.List;

import asura.data.tasks.Task;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {
    String description;

    /**
     * Creates a FindCommand with the specified description.
     * @param description The specified description.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Finds and outputs a list of tasks that match the specified description.
     * @param taskList The list of tasks of the user.
     * @param ui The UI object to give user feedback.
     * @param storage The storage object to save/load tasks.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> filtered = taskList.find(description);
        TaskList temp = new TaskList(filtered);
        ui.printString(temp.toString());
    }

    /**
     * Indicates that the user does not want to terminate the program.
     * @return A boolean representing whether the program is to be terminated, in this case false.
     */
    public boolean isExit() {
        return false;
    }
}
