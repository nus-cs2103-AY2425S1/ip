package waterfall.command;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    public abstract boolean isExit();
}
