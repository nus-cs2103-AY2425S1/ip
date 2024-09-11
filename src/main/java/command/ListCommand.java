package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Represents a command to display the entire list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        // Default constructor
    }

    /**
     * Executes the ListCommand by displaying the entire task list to the user.
     *
     * @param tasks The TaskList to display.
     * @param storage The Storage object, which is not used in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.showTaskList(tasks);
    }
}
