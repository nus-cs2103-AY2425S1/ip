package duck.command;

import duck.task.TaskList;
import duck.ui.Ui;
import duck.storage.Storage;

public class ListCommand implements Command {
    private final String fullCommand;

    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showTasks(list);

    }
}
