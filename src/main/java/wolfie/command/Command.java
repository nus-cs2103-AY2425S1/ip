package wolfie.command;

import java.io.IOException;

import wolfie.exception.WolfieException;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException;
    public boolean isExit() {
        return false;
    } // By default, the program will not exit
}
