package hoshi.command;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * ByeCommand handles the logic for the initialization message
 */
public class ByeCommand implements Command {

    /**
     * Executes the logic for initializing hoshi
     *
     * @param tasks  the TaskList that stores 3 types of tasks
     * @param ui     Ui that handles all user interaction
     * @param storage Storage that handles all input output of Hoshi
     * @return response corresponding to the function that was called depending on user input
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayBye();
    }
}
