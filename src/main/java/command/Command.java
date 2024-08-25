package command;

import java.io.IOException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
    boolean isExit();
}