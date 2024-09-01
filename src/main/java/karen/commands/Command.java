package karen.commands;

import karen.tasks.TaskList;
import karen.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui);

    public abstract boolean isExit();
}
