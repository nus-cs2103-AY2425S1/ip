package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * This class is used to handle mark commands
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for MarkCommand
     * @param index index of ArrayList to obtain task
     */
    public MarkCommand(int index) {
        super("");
        this.index = index;
    }

    /**
     * Executes the mark command
     * @param taskList TaskList object to store task
     * @param ui Ui object to display messages
     * @param storage Storage object to read and write to file
     * @return Task details and execution message
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task marked = taskList.mark(index);
        storage.writeStorage(taskList.getTaskList());
        if (marked != null) {
            return ui.displayMarkedMessage(marked);
        } else {
            return " OOPS!!! The task does not exist.";
        }
    }
}
