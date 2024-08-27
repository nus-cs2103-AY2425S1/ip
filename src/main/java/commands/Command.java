package commands;

import exceptions.DownyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public interface Command {
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException;
    public boolean isExit();

}
