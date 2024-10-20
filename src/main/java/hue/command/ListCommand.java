package hue.command;

import hue.ui.Ui;
import hue.storage.Storage;
import hue.task.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}


