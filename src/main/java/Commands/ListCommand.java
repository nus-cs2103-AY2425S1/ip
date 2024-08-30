package Commands;

import Main.Storage;
import Tasks.TaskList;
import Main.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param tasks   The task list to be displayed.
     * @param ui      The UI component that handles user interaction.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
