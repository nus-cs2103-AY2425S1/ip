package jeriel.command;

import jeriel.util.TaskList;
import jeriel.util.Storage;
import jeriel.util.Ui;

public class ListCommand extends Command {

    /**
     * Shows the current tasks in the task list.
     *
     * @param tasks   the task list to show
     * @param ui      the ui to display the result (not used in GUI)
     * @param storage the storage to save to (not used)
     * @return The string result of the tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return result.toString();
    }
}
