package garfield.commands;

import garfield.exceptions.GarfieldException;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GarfieldException {
        ui.showMessage("Oh, having second thoughts? OK, I've marked that garfield.task as not done yet:\n\n\t"
                + taskList.unmark(taskId) + "\n\nClearly, you're still undecided.");
        storage.save(taskList);
    }
}
