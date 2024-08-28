package command;

import exception.DudeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException;

    public abstract boolean isExit();
}
