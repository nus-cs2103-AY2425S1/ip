package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.LictException;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LictException;
}
