package commands;

import storage.Storage;
import taskList.TaskList;
import tasks.Task;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskAt(taskIndex);
        task.markUndone();
        storage.writeToTextStorage(tasks);
        ui.printGenericMessage( "Ok, I've marked this task as not done yet:\n  " + task);
    }
}
