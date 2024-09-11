package milo.command;

import milo.tasks.TaskList;
import milo.ui.Ui;

public abstract class Command {

    boolean hasError = false;

    String errorDesc;
    public abstract void execute(TaskList taskList);

    public abstract String commandToString(Ui ui, TaskList taskList);
}
