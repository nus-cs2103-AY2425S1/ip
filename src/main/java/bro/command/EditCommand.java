package bro.command;

import bro.BroException;
import bro.task.Task;
import bro.task.TaskList;
import bro.ui.UI;

public class EditCommand implements Command {

    private final TaskList taskList;
    private final boolean isMark;
    private final int taskId;

    public EditCommand(TaskList taskList, boolean isMark, int taskId) {
        this.taskList = taskList;
        this.isMark = isMark;
        this.taskId = taskId;
    }

    public void execute(UI ui) throws BroException{
        try {
            if (this.isMark) {
                Task task = this.taskList.markTask(this.taskId);
                ui.showMarkTaskSuccess(task);
            } else {
                Task task = this.taskList.unmarkTask(this.taskId);
                ui.showUnmarkTaskSuccess(task);
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
