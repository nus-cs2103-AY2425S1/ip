package stobberi.command;

import stobberi.components.TaskList;

/**
 * Represents a command to mark a task as completed in a {@link TaskList}.
 */
public class MarkCommand extends Command {
    /**
     * The list of tasks where the task will be marked as completed.
     */
    private TaskList taskList;

    /**
     * The number of the task to be marked as completed.
     */
    private int taskNumber;

    /**
     * Constructs a new {@code MarkCommand} with the specified {@link TaskList} and task number.
     *
     * @param taskList   The list of tasks where the task will be marked as completed.
     * @param taskNumber The number of the task to be marked as completed.
     */
    public MarkCommand(TaskList taskList, int taskNumber) {
        this.taskList = taskList;
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command by marking the specified task as completed in the {@link TaskList}.
     */
    @Override
    public void execute() {
        taskList.markTask(taskNumber);
    }
}