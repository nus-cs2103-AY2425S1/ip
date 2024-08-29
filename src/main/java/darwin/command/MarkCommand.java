package darwin.command;

import darwin.exception.IllegalTaskNumberException;
import darwin.task.Task;
import darwin.task.TaskManager;
import darwin.ui.Ui;

public class MarkCommand extends Command {

    public static final String CMD_WORD = "mark";
    private static final String MARK_MSG = "Nice! I've marked this task as done:";

    private final int taskIdx;

    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws IllegalTaskNumberException {
        Task task = taskManager.markTask(this.taskIdx);
        ui.send(MARK_MSG + "\n" + task.getTaskInfo());
    }
}
