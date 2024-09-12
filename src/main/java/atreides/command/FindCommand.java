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
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        assert !description.isEmpty();
        return tasks.find(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        String response = executeString(tasks, ui, storage);
        ui.showMessage(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
