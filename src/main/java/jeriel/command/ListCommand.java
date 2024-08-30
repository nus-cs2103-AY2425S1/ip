package jeriel.command;
import jeriel.task.*;
import jeriel.util.*;
public class ListCommand extends Command {
    /**
     * Shows the current tasks in the task list.
     *
     * @param tasks the task list to show
     * @param ui the ui to display the result
     * @param storage the storage to save to (not used)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
