package Karen.commands;

import Karen.tasks.TaskList;
import Karen.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui);

    public abstract boolean isExit();
}
