package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

import java.util.Map;

public abstract class Command {
    protected final Map<String, String> arguments;

    public Command(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
