package LBot.command;

import LBot.exception.FileException;
import LBot.helper.Ui;
import LBot.helper.Storage;
import LBot.helper.TaskList;

import LBot.exception.ExecuteCommandException;

// role of this task is to create the tasks or run the command
public abstract class Command {
    // doesn't contain any information
    // dont need constructor
    public abstract void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException;
}
