package park.commands;

import park.storage.Storage;
import park.storage.TaskList;
import park.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showToUser("There are no tasks in your list.");
        } else {
            ui.showToUser("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showToUser((i + 1) + "." + tasks.get(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
