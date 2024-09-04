package park.commands;

import park.storage.Storage;
import park.storage.TaskList;
import park.ui.Ui;

/**
 * Represents a command that shows the user the current list of tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.setResponse("There are no tasks in your list.");
        } else {
            ui.setResponse("Here are the tasks in your list:" + "\n");
            for (int i = 0; i < tasks.size(); i++) {
                ui.setResponse((i + 1) + "." + tasks.get(i) + "\n");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
