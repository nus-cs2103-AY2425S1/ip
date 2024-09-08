package darwin.command;

import darwin.task.Task;
import darwin.task.TaskManager;
import darwin.ui.Ui;

/**
 * AddTaskCommand class to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    private static final String ADD_TASK_MSG = "Got it. I've added this task:";
    private static final String TASK_COUNT_MSG = "Now you have %d tasks in the list.";
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add a task to the task list.
     * @param taskManager task manager to add task to
     * @param ui ui to send messages to user
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        taskManager.addTask(this.task);
        // TODO: reduce repeated task count msg code in delete command
        ui.send(String.format("%s\n    %s\n%s",
                ADD_TASK_MSG,
                task.getTaskInfo(),
                String.format(TASK_COUNT_MSG, taskManager.getTaskCount())
        ));
    }
}
