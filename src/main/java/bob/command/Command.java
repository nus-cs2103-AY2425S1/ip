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

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Command)) {
            return false;
        }

        Command command = (Command) object;

        try {
            this.getClass().cast(command);
        } catch (ClassCastException e) {
            return false;
        }

        return this.arguments.equals(command.arguments);
    }
}
