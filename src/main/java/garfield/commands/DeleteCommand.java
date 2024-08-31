package garfield.commands;

import garfield.exceptions.GarfieldException;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

public class DeleteCommand extends Command {

    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GarfieldException {
        ui.showMessage("Alright you've got 1 less garfield.task.\n\n\t"
                + taskList.delete(taskId) + "\n\nEnjoy the extra ‘fun’ —or whatever you call it.");
        storage.save(taskList);

    }
}
