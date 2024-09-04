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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
