package stobberi.command;

import stobberi.components.TaskList;

/**
 * Represents a command to delete a task from a {@link TaskList} based on the task number.
 */
public class DeleteCommand extends Command {
    /**
     * The list of tasks from which the task will be deleted.
     */
    private TaskList taskList;

    /**
     * The number of the task to be deleted.
     */
    private int taskNumber;

    /**
     * Constructs a new {@code DeleteCommand} with the specified {@link TaskList} and task number.
     *
     * @param taskList   The list of tasks from which the task will be deleted.
     * @param taskNumber The number of the task to be deleted.
     */
    public DeleteCommand(TaskList taskList, int taskNumber) {
        this.taskList = taskList;
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command by deleting the task from the {@link TaskList} based on the task number.
     */
    @Override
    public void execute() {
        taskList.delete(taskNumber);
    }
}