package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class UpdateCommand extends Command {
    
    private int index;
    private String startDate;
    private String endDate;
    private String deadline;
    public UpdateCommand(int index, String startDate, String endDate) {
        super("");
        this.index = index;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public UpdateCommand(int index, String deadline) {
        super("");
        this.index = index;
        this.deadline = deadline;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (startDate != null && endDate != null) {
            return getEventUpdate(taskList, ui, storage);
        } else {
            return getDeadlineUpdate(taskList, ui, storage);
        }
    }

    private String getDeadlineUpdate(TaskList taskList, Ui ui, Storage storage) {
        Task updated = taskList.updateTask(index, deadline);
        storage.writeStorage(taskList.getTaskList());
        if (updated != null) {
            return ui.displayUpdatedMessage(updated);
        } else {
            return " OOPS!!! The task does not exist.";
        }
    }

    private String getEventUpdate(TaskList taskList, Ui ui, Storage storage) {
        Task updated = taskList.updateTask(index, startDate, endDate);
        storage.writeStorage(taskList.getTaskList());
        if (updated != null) {
            return ui.displayUpdatedMessage(updated);
        } else {
            return " OOPS!!! The task does not exist.";
        }
    }
}
