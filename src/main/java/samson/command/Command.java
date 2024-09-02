package samson.command;

import samson.Storage;
import samson.Ui;
import samson.task.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    public abstract boolean isExit();
}
