package dumpling.command;

import dumpling.task.TaskList;
import dumpling.Ui;
import dumpling.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
