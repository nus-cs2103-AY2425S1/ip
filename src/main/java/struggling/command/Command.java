package struggling.command;
import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return false;
    }
}
