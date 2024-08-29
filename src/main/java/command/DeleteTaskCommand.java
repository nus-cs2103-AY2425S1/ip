package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(String indexString) {
        this.taskIndex = Integer.parseInt((indexString));
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(taskIndex);
    }
}
