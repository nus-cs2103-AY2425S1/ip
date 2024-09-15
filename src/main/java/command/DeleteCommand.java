package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * This class is used to handle delete commands
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand
     * @param index
     */
    public DeleteCommand(int index) {
        super("");
        this.index = index;
    }

    /**
     * Executes the delete command
     * @param taskList tasklist Object to store task
     * @param ui ui object to display messages
     * @param storage storage object to read and write to file
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task marked = taskList.delete(index);
        assert marked != null : "Task should not be null";
        storage.writeStorage(taskList.getTaskList());
        if (marked != null) {
            return ui.displayDeletedMessage(marked, taskList.size());
        } else {
            return " OOPS!!! The task does not exist.";
        }
    }
}
