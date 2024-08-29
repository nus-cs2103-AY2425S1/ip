package Stobberi.Command;

import Stobberi.components.TaskList;

import Stobberi.components.TaskList;

/**
 * Represents a command to mark a task as incomplete in a {@link TaskList}.
 */
public class UnmarkCommand extends Command {
    /**
     * The list of tasks where the task will be marked as incomplete.
     */
    private TaskList taskList;

    /**
     * The number of the task to be marked as incomplete.
     */
    private int taskNumber;

    /**
     * Constructs a new {@code UnmarkCommand} with the specified {@link TaskList} and task number.
     *
     * @param taskList   The list of tasks where the task will be marked as incomplete.
     * @param taskNumber The number of the task to be marked as incomplete.
     */
    public UnmarkCommand(TaskList taskList, int taskNumber) {
        this.taskList = taskList;
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command by marking the specified task as incomplete in the {@link TaskList}.
     */
    @Override
    public void execute() {
        taskList.unmarkTask(taskNumber);
    }
}