package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

public abstract class Command {
    StringBuilder output = new StringBuilder();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AsuraException;

    public abstract boolean isExit();
}
