package bro.command;

import bro.BroException;
import bro.task.TaskList;

public class EditCommand implements Command {

    private final TaskList taskList;
    private final boolean isMark;
    private final int taskId;

    public EditCommand(TaskList taskList, boolean isMark, int taskId) {
        this.taskList = taskList;
        this.isMark = isMark;
        this.taskId = taskId;
    }

    public void execute() throws BroException{
        try {
            if (this.isMark) {
                this.taskList.markTask(this.taskId);
            } else {
                this.taskList.unmarkTask(this.taskId);
            }

            //TODO: Storage

        } catch (Exception e) {
            throw new BroException("Error Marking task");
        }
    }

    public boolean isExit() {
        return false;
    }
}
