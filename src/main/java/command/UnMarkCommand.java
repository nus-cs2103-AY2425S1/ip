package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * This class is used to handle unmark commands
 */
public class UnMarkCommand extends Command {
    private int index;

    /**
     * Constructor for UnMarkCommand
     * @param index index of the task to be unmarked
     */
    public UnMarkCommand(int index) {
        super("");
        this.index = index;
    }

    /**
     * Executes the unmark command
     * @param taskList
     * @param ui
     * @param storage
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task marked = taskList.unmark(index);
        storage.writeStorage(taskList.getTaskList());
        if (marked != null) {
            return ui.displayUnmarkedMessage(marked);
        } else {
            return " OOPS!!! The task does not exist.";
        }
    }
}
