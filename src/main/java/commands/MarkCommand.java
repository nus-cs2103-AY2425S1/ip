package commands;

import storage.Storage;
import taskList.TaskList;
import tasks.Task;
import ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskAt(taskIndex);
        task.markDone();
        storage.writeToTextStorage(tasks);
        ui.printGenericMessage( "Nice! I've marked this task as done:\n  " + task);
    }
}
