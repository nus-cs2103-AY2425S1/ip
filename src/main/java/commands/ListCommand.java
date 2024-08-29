package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.print("No items have been added to the task list.");
        } else {
            ui.print(tasks.listAllTasks());
        }
    }
}
