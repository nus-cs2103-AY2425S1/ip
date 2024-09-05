package snowy.common;

import snowy.data.SnowyException;
import snowy.tasklist.TaskList;

public class Command {
    protected TaskList taskList;

    public Command() {}

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    public CommandResult execute() throws SnowyException {
        throw new SnowyException("No execution");
    }

    public boolean isExit() {
        return false;
    }
}
