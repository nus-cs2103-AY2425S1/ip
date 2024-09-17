package main.command;

import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

/**
 * Command that lists out tasks written into storage.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
