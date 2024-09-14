package skibidi.command;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.Ui;

/** Abstract class for a Command to be executed on tasks. */
public abstract class AbstractCommand {
    /** Execute the command and return string message to be printed. */
    public abstract String execute(TaskList taskList, Storage storage, Ui ui);
}
