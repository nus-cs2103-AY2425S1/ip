package mylo.command;

import mylo.data.InsufficientInfoException;
import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.ui.Ui;
import mylo.utils.exceptions.IllegalValueException;

public abstract class Command {
    private String message;

    public abstract void execute(TaskList list, Ui ui) throws StorageOperationException, InsufficientInfoException, IllegalValueException, IndexOutOfBoundsException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
