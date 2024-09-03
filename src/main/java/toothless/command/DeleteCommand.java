package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.exceptions.MissingIndexExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.ui.Ui;

public class DeleteCommand extends Command {
    private String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if(description.isEmpty()) {
            throw new MissingIndexExceptions("delete", "delete <index>");
        }
        int deleteIndex = Integer.parseInt(description);
        taskList.deleteTask(deleteIndex);
        storage.saveTask(taskList.getList());
    }
}
