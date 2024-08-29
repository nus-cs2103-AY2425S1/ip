package commands;

import exceptions.InvalidCommandException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, IOException;
    public boolean isExit() {
        return false;
    }
}
