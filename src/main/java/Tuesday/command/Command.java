package Tuesday.command;

import Tuesday.util.Storage;
import Tuesday.task.Task;
import Tuesday.util.Ui;

public abstract class Command {
    // description of the command
    private String command;
    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(Task task, Ui ui, Storage storage);

    public abstract boolean isExit();
}
