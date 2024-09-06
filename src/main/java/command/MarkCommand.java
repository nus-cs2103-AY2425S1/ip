package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        super("");
        this.index = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task marked = taskList.mark(index);
        storage.writeStorage(taskList.getTaskList());
        if (marked != null) {
            return ui.displayMarkedMessage(marked);
        } else {
            return " OOPS!!! The task does not exist.";
        }
    }
}
