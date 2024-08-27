package gray.command;

import gray.GrayException;
import gray.Ui;
import gray.task.TaskList;

public abstract class Command {
    public void execute(Ui ui, TaskList tasks) throws GrayException { }
    public boolean isExit() { return false; }
}
