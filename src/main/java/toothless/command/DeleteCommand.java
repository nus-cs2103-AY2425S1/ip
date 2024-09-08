package toothless.command;

import toothless.exceptions.MissingIndexExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private String description;

    /**
     * Constructor for DeleteCommand.
     *
     * @param description Description of the task to be deleted.
     *                    Should be the index of the task.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if (description.isEmpty()) {
            throw new MissingIndexExceptions("delete", "delete <index>");
        }
        int deleteIndex = Integer.parseInt(description);
        String response = taskList.deleteTask(deleteIndex);
        storage.saveTask(taskList.getList());
        return response;
    }
}
