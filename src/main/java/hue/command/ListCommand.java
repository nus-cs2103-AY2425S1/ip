package hue.command;

import hue.ui.ui;
import hue.storage.Storage;
import hue.task.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}


