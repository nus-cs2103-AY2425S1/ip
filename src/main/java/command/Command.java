package command;

import exception.FileException;
import helper.Ui;
import helper.Storage;
import helper.TaskList;

import exception.ExecuteCommandException;

// role of this task is to create the tasks or run the command
public abstract class Command {
    // doesn't contain any information
    // dont need constructor
    public abstract void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException;
}
