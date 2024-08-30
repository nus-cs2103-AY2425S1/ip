package snowy.common;

import snowy.data.SnowyException;
import snowy.tasklist.TaskList;

public class Command {
    protected TaskList taskList;

    // Constructor to initialize the command with a command word
    public Command() {}

    // Set the TaskList for the command
    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    // The execute method now returns a CommandResult
    public CommandResult execute() throws SnowyException {
        throw new SnowyException("No execution");
    };

    public boolean isExit() {
        return false;
    }
}

