package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws TalkerException;

    public boolean isExit() {
        return false;
    }
}
