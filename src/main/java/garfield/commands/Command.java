package garfield.commands;

import garfield.exceptions.GarfieldException;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

public abstract class Command {
    public Command() {}
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws GarfieldException {
    }
}
