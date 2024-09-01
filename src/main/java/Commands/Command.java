package Commands;

import Exceptions.BrockException;
import Tasks.TaskList;
import Storage.Storage;
import Ui.Ui;

public abstract class Command {
    private final String command;

    protected Command(String command) {
        this.command = command;
    }

    protected String getCommand() {
        return this.command;
    }

    abstract public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException;

    abstract public boolean isExit();
}
