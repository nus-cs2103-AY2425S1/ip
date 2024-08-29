package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkTaskCommand extends Command {
    private final int taskIndex;

    public UnmarkTaskCommand(String arguments) {
        this.taskIndex = Integer.parseInt((arguments));
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmarkAsDone(taskIndex);
    }
}
