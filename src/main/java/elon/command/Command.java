package elon.command;

import elon.Storage;
import elon.task.TaskList;
import elon.Ui;

public abstract class Command {
    public abstract String execute(TaskList list, Ui ui, Storage storage) throws Exception;
}
