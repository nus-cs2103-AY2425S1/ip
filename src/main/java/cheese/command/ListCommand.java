package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;

/**
 * Command to list all tasks
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand
     */
    public ListCommand() {}

    /**
     * Returns formatted String from the Ui of the TaskList
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     * @return String
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.say(tasks);
    }
}
