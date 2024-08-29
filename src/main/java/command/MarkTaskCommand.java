package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkTaskCommand extends Command {
    private final int taskIndex;

    public MarkTaskCommand(String arguments) {
        this.taskIndex = Integer.parseInt((arguments));
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(taskIndex);
    }
}
