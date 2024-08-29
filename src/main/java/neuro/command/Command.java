package neuro.command;

import neuro.Ui;
import neuro.Storage;

import neuro.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
