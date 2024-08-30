package kobe.command;

import kobe.util.Storage;
import kobe.task.TaskList;
import kobe.util.Ui;

import java.io.*;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return false;
    }
}