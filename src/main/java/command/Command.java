package command;
import java.io.IOException;

import task.TaskList;

import exception.ScheduloException;

import util.Storage;

import util.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ScheduloException, IOException;
    public boolean isExit() {
        return false;
    }
}