package friday.command;

import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
