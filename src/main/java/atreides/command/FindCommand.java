package atreides.command;

import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

public class FindCommand implements Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        String response = tasks.find(description);
        ui.showMessage(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
