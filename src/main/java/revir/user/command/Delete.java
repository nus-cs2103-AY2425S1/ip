package revir.user.command;

import java.io.IOException;

import revir.tasks.TaskList;
import revir.user.ui.Ui;

/**
 * Represents a command to remove a task from the task list.
 */
public class Delete extends Command {
    private int taskIndex;

    /**
     * Constructs a new Delete command with the specified task index.
     *
     * @param taskIndex the index of the task to be deleted
     */
    public Delete(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command, which removes a task from the task list.
     *
     * @param ui       The user interface object used to display the result of the
     *                 command.
     * @param taskList The task list object from which the task will be removed.
     * @throws IOException If an I/O error occurs while executing the command.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws IOException {
        ui.showResult(taskList.remove(this.taskIndex));
    }
}
