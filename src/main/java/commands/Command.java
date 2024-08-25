package commands;

import exceptions.InvalidCommandException;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui) throws InvalidCommandException;

    public boolean isExit() {
        return false;
    }
}
