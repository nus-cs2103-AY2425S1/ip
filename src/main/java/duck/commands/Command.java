package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;

public abstract class Command {

    protected String message;

    public Command(String message) {
        this.message = message;
    }

    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException;

    public abstract boolean isExit();
}
