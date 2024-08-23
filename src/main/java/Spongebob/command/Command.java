package Spongebob.command;

import Spongebob.storage.Storage;
import Spongebob.storage.TaskList;
import Spongebob.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
