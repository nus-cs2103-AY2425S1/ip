package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

/**
 * An abstract class representing a user command.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
