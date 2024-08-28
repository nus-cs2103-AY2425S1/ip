package skywalker.command;
import skywalker.storage.Storage;
import skywalker.task.TaskList;
import skywalker.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IOException;
    public boolean isExit() {
        return false;
    }
}
