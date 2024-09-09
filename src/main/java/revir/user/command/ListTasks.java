package revir.user.command;

import revir.tasks.TaskList;
import revir.user.ui.Ui;

/**
 * Represents a command to list tasks.
 */
public class ListTasks extends Command {
    /**
     * Represents a command to list tasks.
     * This command is used to retrieve a list of tasks.
     */
    public ListTasks() {
        super(false);
    }

    /**
     * Executes the command to list all tasks.
     *
     * @param ui       The user interface to display the result.
     * @param taskList The task list containing the tasks to be listed.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        String list = taskList.list();
        ui.showResult("List:\n" + list);
    }
}
