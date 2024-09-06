package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class UnMarkCommand extends Command {
    private int index;

    public UnMarkCommand(int index) {
        super("");
        this.index = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task marked = taskList.unmark(index);
        storage.writeStorage(taskList.getTaskList());
        if (marked != null) {
            return ui.displayUnmarkedMessage(marked);
        } else {
            return " OOPS!!! The task does not exist.";
        }
    }
}
