package hue.command;

import hue.UI.UI;
import hue.storage.Storage;
import hue.task.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}


