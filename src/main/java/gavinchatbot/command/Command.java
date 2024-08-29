package gavinchatbot.command;

import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;
import java.io.IOException;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException;
    boolean isExit();
}
