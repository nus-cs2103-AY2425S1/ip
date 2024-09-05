package LittleMissHelpful.Command;

import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.TaskList;
import LittleMissHelpful.Ui;
import LittleMissHelpful.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException;
    public abstract boolean isExit();
}
