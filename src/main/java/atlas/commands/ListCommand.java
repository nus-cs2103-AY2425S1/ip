package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

public class ListCommand extends Command {
    /**
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.print("No items have been added to the task list.");
        } else {
            ui.print(tasks.listAllTasks());
        }
    }
}
