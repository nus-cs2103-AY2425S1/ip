package NextGPT.command;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;
import NextGPT.NextGPTException;

/**
 * Subclass of Command that displays content of task list.
 */
public class ListCommand extends Command{
    /**
     * Displays content of task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        ui.showList(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
