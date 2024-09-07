package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.ui.Ui;
import noosy.task.TaskList;


public abstract class Command {


    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException;

    public boolean isExit() {
        return false;
    }
}
