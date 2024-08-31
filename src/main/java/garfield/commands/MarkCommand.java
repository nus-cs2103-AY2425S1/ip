package garfield.commands;

import garfield.exceptions.GarfieldException;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

public class MarkCommand extends Command {

    private int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GarfieldException {
        ui.showMessage("Nice. You actually did something. I've marked that garfield.task as done:\n\n\t"
                + taskList.mark(taskId));
        storage.save(taskList);

    }
}
