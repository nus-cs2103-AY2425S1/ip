package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
