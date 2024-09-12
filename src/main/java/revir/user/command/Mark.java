package revir.user.command;

import java.io.IOException;

import revir.tasks.TaskList;
import revir.user.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 */
public class Mark extends Command {
    private int taskIndex;
    private boolean state;

    /**
     * Creates a new Mark object with the specified task index and state.
     *
     * @param taskIndex the index of the task to mark
     * @param state the state to set for the task (true for marked, false for unmarked)
     */
    public Mark(int taskIndex, boolean state) {
        super(false);
        this.state = state;
    }

    /**
     * Executes the command to mark a task as completed.
     *
     * @param ui The user interface to display the result.
     * @param taskList The task list containing the tasks.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws IOException {
        ui.showResult(taskList.setCompleted(taskIndex, state));
    }

}
