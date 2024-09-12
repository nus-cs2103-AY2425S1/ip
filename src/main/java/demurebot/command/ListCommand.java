package demurebot.command;

import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
    }

    /**
     * Returns a string representation of the ListCommand object.
     *
     * @return String representation of the ListCommand object.
     */
    @Override
    public String execute(TaskList list, Ui ui) {
        if (list.getSize() == 0) {
            return ui.displayEmptyList();
        }
        return ui.displayList(list);
    }
}
