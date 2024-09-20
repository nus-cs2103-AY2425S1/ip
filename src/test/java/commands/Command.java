package commands;

import exceptions.HandsomeException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, HandsomeException;
    public boolean isExit() {
        return false;
    }
}
