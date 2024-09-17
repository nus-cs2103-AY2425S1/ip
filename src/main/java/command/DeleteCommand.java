package command;

import exception.CommandFoundButInvalidException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * A Command to delete a task from the task list
 */
public class AddDeleteCommand {
    private String description;

    /**
     * 
     * @param description
     */
    public AddDeleteCommand(String description) {
        this.description = description;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws CommandFoundButInvalidException {
        taskList.delete(this.description);
        storage.put(taskList);
        return ui.deleteMessage(taskList.getLastDeleted(), taskList.getSize());
    }
}
