package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super("");
        this.index = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task marked = taskList.delete(index);
        storage.writeStorage(taskList.getTaskList());
        if (marked != null) {
            return ui.displayDeletedMessage(marked, taskList.size());
        } else {
            return " OOPS!!! The task does not exist.";
        }
    }
}
