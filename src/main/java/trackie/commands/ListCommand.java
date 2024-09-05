package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.Ui;

/**
 * Represents a command to list out all the tasks in the tasklist at a given point in time.
 */
public class ListCommand extends Command {
    /**
     * Creates a new ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the list command.
     * This method causes the tasklist to list out all the tasks that are stored in it
     * at the point in time.
     *
     * @param tasklist The TaskList object from which a task will be deleted.
     * @param ui The Ui object used to display messages to the user.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.listTasks();
    }
}
