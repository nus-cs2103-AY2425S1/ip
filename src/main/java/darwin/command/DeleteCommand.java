package darwin.command;

import darwin.exception.IllegalTaskNumberException;
import darwin.task.Task;
import darwin.task.TaskManager;
import darwin.ui.Ui;

public class DeleteCommand extends Command {

    public static final String CMD_WORD = "delete";
    private static final String DELETE_TASK_MSG = "Noted. I've removed this task:";

    private static final String TASK_COUNT_MSG = "Now you have %d tasks in the list.";
    private final int taskIdx;

    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws IllegalTaskNumberException {
        Task task = taskManager.deleteTask(this.taskIdx);
        ui.send(String.format("%s\n    %s\n%s", DELETE_TASK_MSG, task.getTaskInfo(),
                String.format(TASK_COUNT_MSG, taskManager.getTaskCount())));
    }
}
