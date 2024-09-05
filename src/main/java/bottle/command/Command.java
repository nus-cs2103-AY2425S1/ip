package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

/**
 * The type Command.
 */
public abstract class Command {
    /**
     * Execute.
     *
     * @param taskList the task list
     * @param ui       the ui
     * @param storage  the storage
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
