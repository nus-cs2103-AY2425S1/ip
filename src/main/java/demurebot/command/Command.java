package demurebot.command;

import demurebot.DemureBotException;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList list, Ui ui) throws DemureBotException;
}
