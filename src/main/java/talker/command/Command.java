package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents a command to be executed based on input from user
 */
public abstract class Command {

    public abstract String execute(TaskList list, Ui ui, Storage storage) throws TalkerException;

    public boolean isExit() {
        return false;
    }
}
