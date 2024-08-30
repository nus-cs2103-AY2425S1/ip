package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage);

    public abstract boolean isExit();
}
