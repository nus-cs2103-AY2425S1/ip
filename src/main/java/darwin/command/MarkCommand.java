package darwin.command;

import darwin.exception.IllegalTaskNumberException;
import darwin.task.Task;
import darwin.task.TaskManager;
import darwin.ui.Ui;

/**
 * MarkCommand class to represent a command to mark a task as done.
 */
public class MarkCommand extends Command {
    public static final String CMD_WORD = "mark";
    private static final String MARK_MSG = "Nice! I've marked this task as done:";
    private final int taskIdx;

    /**
     * Creates a new mark command.
     * @param taskIdx index of the task to mark
     */
    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Executes the mark command.
     * @param taskManager task manager to execute the command
     * @param ui user interface to display messages
     * @throws IllegalTaskNumberException if the task number is invalid
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws IllegalTaskNumberException {
        Task task = taskManager.markTask(this.taskIdx);
        ui.send(MARK_MSG + "\n" + task.getTaskInfo());
    }
}
