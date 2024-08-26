package friday.command;

import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException;

    public boolean isExit() {
        return false;
    }
}


