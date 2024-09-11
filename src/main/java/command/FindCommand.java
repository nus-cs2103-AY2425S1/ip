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
     * Finds tasks that contain the keyword, displays the tasks to the user on the CLI,
     * and does not save the task list to file.
     *
     * @param taskList The task list to be searched by the command.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showFindList(taskList.getTasks(), keyword);
    }

    /**
     * Finds tasks that contain the keyword, displays the tasks to the user on the GUI,
     * and does not save the task list to file.
     *
     * @param taskList The task list to be searched by the command.
     * @param gui The GUI to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @return The list of tasks that contain the keyword.
     */
    @Override
    public String executeGui(TaskList taskList, UiGui gui, Storage storage) {
        return gui.showFindList(taskList.getTasks(), keyword);
    }

}
