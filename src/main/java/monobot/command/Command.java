package monobot.command;

import monobot.exception.MonoBotException;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents commands in the abstract Command class.
 */
public abstract class Command {
    protected final CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException;
}
