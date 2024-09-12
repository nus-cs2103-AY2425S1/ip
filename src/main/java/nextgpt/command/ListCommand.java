package nextgpt.command;

import nextgpt.TaskList;
import nextgpt.Ui;
import nextgpt.Storage;



/**
 * Subclass of Command that displays content of task list.
 */
public class ListCommand extends Command {
    /**
     * Outputs list of events that user has saved
     *
     * @return String message with list of events that user has saved
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
