package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.exceptions.ToothlessExceptions;
import toothless.ui.Ui;

public abstract class Command {
    public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage) throws ToothlessExceptions;
}
