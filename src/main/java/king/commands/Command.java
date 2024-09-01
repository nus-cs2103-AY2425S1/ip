package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KingException;
    public abstract boolean isExit();
}

