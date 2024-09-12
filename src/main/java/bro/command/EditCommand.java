package bro.command;

import bro.BroException;
import bro.storage.Storage;
import bro.task.Task;
import bro.task.TaskList;
import bro.ui.UI;

public class EditCommand implements Command {

    private final TaskList taskList;
    private final boolean isMark;
    private final int taskId;
    private final Storage storage;

    public EditCommand(TaskList taskList, boolean isMark, int taskId, Storage storage) {
        this.taskList = taskList;
        this.isMark = isMark;
        this.taskId = taskId;
        this.storage = storage;
    }

    public String execute(UI ui) throws BroException{
        try {
            String response;
            if (this.isMark) {
                Task task = this.taskList.markTask(this.taskId);
                response = ui.showMarkTaskSuccess(task);
            } else {
                Task task = this.taskList.unmarkTask(this.taskId);
                response = ui.showUnmarkTaskSuccess(task);
            }

            this.storage.writeToStorage(this.taskList.getTasks());
            assert !response.isEmpty();
            return response;

        } catch (Exception e) {
            throw new BroException("Error Marking task");
        }
    }

    public boolean isExit() {
        return false;
    }
}
