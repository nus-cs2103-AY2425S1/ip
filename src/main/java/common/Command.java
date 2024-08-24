package common;

import storage.TaskStorage;

import java.io.IOException;

public abstract class Command {
    public abstract boolean execute(Ui ui, TaskStorage storage) throws SkibidiException;
}