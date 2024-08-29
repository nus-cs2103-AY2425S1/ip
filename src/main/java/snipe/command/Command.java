package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException;

    public boolean isExit() {
        return false;  // Default behavior is that the snipe.command is not an exit snipe.command
    }
}