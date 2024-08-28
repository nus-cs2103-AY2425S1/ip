package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

abstract public class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
