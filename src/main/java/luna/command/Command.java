package luna.command;

import luna.Storage;
import luna.TaskList;
import luna.LunaException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage) throws LunaException;
}
