package gavinchatbot.command;

import gavinchatbot.task.TaskList;
import gavinchatbot.util.Ui;
import gavinchatbot.util.Storage;
import gavinchatbot.util.GavinException;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException {
        try {
            ui.showList(tasks);
        } catch (GavinException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
