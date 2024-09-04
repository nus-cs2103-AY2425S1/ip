package command;

import data.InsufficientInfoException;
import storage.StorageOperationException;
import task.TaskList;
import ui.Ui;
import utils.exceptions.IllegalValueException;

public abstract class Command {
    private String message;

    public abstract void execute(TaskList list, Ui ui) throws StorageOperationException, InsufficientInfoException, IllegalValueException, IndexOutOfBoundsException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
