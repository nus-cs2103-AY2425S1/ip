package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.show("There are currently no items in the list.");
        } else {
            tasks.printTasks(ui);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
