package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.BeeBooExceptions;

public abstract class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }
    public Command() {
        command = null;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BeeBooExceptions;

    public abstract boolean isExit();

}
