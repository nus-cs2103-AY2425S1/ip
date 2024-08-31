package main.java.commands;

import main.java.TaskList;
import main.java.util.Storage;
import main.java.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
