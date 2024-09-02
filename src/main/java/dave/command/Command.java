package dave.command;

import java.io.IOException;
import dave.task.TaskList;
import dave.storage.Storage;
import dave.ui.Ui;
public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws IOException;
    public boolean isExit() {
        return false;
    }
}