package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.TaskList;
import Majima.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException;

    public abstract boolean isExit();
}
