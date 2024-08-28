package alex.command;

import java.io.IOException;


import alex.TaskList;
import alex.Ui;
import alex.Storage;
import alex.AlexException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException;

    public abstract boolean isExit();
}
