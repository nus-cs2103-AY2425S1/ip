package friday.command;

import friday.Storage;
import friday.TaskList;
import friday.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
