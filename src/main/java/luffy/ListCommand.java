package luffy;

/**
 * Represents a command that displays the list of tasks
 * user has inputted
 */
public class ListCommand extends Command {

    /**
     * This method adds an executable command to display
     * the task list by the UI
     *
     * @param ui user interface for Chat Bot
     * @param taskStorage storage location for file
     * @param taskList array list of existing tasks
     */
    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        ui.showLine();
        ui.displayTasks(taskList);
        ui.showLine();
    }
}