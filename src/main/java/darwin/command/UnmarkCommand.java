package darwin.command;

import darwin.exception.IllegalTaskNumberException;
import darwin.task.Task;
import darwin.task.TaskManager;
import darwin.ui.Ui;

public class UnmarkCommand extends Command {

    public static final String CMD_WORD = "unmark";

    private static final String UNMARK_MSG = "OK, I've marked this task as not done yet:";

    private final int taskIdx;

    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws IllegalTaskNumberException {
        Task task = taskManager.unmarkTask(this.taskIdx);
        ui.send(UNMARK_MSG + "\n" + task.getTaskInfo());
    }
}
