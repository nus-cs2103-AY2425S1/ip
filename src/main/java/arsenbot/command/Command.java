package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.TaskList;
import arsenbot.task.TaskManagerException;
import arsenbot.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TaskManagerException;

    public boolean isExit() {
        return false;
    }
}

