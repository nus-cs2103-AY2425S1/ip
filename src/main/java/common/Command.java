package common;

import storage.TaskStorage;

public abstract class Command {
    public abstract boolean execute(Ui ui, TaskStorage storage);
}