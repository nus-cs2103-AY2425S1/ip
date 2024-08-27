package Tick.commands;

import Tick.exceptions.TickException;
import Tick.storage.Storage;
import Tick.tasks.TaskList;
import Tick.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TickException;

    public abstract boolean isExit();
}
