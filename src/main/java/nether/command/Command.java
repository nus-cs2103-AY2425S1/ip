package nether.command;

import nether.NetherException;
import nether.Ui;
import nether.storage.Storage;
import nether.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NetherException;

    public boolean isExit() {
        return false;
    }

}
