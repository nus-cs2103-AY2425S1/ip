package darwin.command;

import darwin.exception.IllegalTaskNumberException;
import darwin.task.Task;
import darwin.task.TaskManager;
import darwin.ui.Ui;

/**
 * UnmarkCommand class to represent a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    public static final String CMD_WORD = "unmark";
    private static final String UNMARK_MSG = "OK, I've marked this task as not done yet:";
    private final int taskIdx;

    /**
     * Creates a new unmark command.
     * @param taskIdx index of the task to unmark
     */
    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Executes the unmark command.
     * @param taskManager task manager to execute the command
     * @param ui user interface to display messages
     * @throws IllegalTaskNumberException if the task index is invalid
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws IllegalTaskNumberException {
        Task task = taskManager.unmarkTask(this.taskIdx);
        ui.send(UNMARK_MSG + "\n" + task.getTaskInfo());
    }
}
