package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AstorException, IOException;
    public abstract boolean isExit();
}
