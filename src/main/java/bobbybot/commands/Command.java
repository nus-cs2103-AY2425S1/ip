package bobbybot.commands;

import bobbybot.DukeException;
import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

public abstract class Command {
    private boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public void setExit() {
        isExit = true;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
