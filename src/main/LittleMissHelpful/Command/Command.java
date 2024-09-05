package main.LittleMissHelpful.Command;

import main.LittleMissHelpful.Exception.InvalidCommandException;
import main.LittleMissHelpful.TaskList;
import main.LittleMissHelpful.Ui;
import main.LittleMissHelpful.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException;
    public abstract boolean isExit();
}
