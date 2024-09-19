package tecna.command;

import tecna.collection.TaskList;
import tecna.storage.Storage;
import tecna.ui.Ui;

/**
 * Represents a Command of type ListCommand (display all the tasks in the list).
 *
 * @author Adapted from Feng1231.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand instance.
     *
     * @param message The whole command input in String.
     */
    public ListCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        return ui.printItems(taskList);
    }
}
