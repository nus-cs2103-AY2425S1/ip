package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

/**
 * Represents a command to find tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays all tasks that contain the specified keyword.
     *
     * @param taskList The task list that stores all tasks.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showFindList(taskList.getTasks(), keyword);
    }

    @Override
    public String executeGui(TaskList taskList, UiGui gui, Storage storage) {
        return gui.showFindList(taskList.getTasks(), keyword);
    }

}
