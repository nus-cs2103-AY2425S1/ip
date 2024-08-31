package Edith.command;

import Edith.Ui;
import Edith.Storage;
import Edith.EdithException;
import Edith.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException;

    public boolean isExit() {
        return false;
    }
}
