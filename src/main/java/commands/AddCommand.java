package commands;

import storage.Storage;
import tasks.Task;
import taskList.TaskList;
import ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.printGenericMessage();
    }
}
