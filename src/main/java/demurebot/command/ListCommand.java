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
        super(false);
    }

    /**
     * Returns a string representation of the ListCommand object.
     *
     * @return String representation of the ListCommand object.
     */
    @Override
    public void execute(TaskList list, Ui ui) {
        if (list.getSize() == 0) {
            ui.displayEmptyList();
        } else {
            ui.displayList(list);
        }
    }
}
