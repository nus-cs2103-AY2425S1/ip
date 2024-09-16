package command;

import tasklist.TaskList;
import exception.TaskListException;
import ui.Ui;

public interface Command {
    public abstract void execute(TaskList tasks, Ui ui) throws TaskListException;
    public abstract boolean isExit();
}
