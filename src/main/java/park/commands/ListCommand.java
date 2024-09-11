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
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        ui.setListResponse(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
