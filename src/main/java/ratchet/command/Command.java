package ratchet.command;

import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList tasks, Ui ui);

    public abstract boolean isExit();
}
