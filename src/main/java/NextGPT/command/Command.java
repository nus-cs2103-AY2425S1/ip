package NextGPT.command;

import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;
import NextGPT.NextGPTException;
public abstract class Command {
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws NextGPTException;
    public abstract boolean isExit();
}
