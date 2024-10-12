package evan.command;

import evan.exception.NoSuchTaskException;
import evan.service.TaskList;

/**
 * Represents a command that the chatbot can execute to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Instantiates a DeleteCommand object.
     *
     * @param taskNumber Task number of the task to delete.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws NoSuchTaskException {
        taskList.deleteTask(taskNumber);
        return "Okay, task (" + taskNumber + ") has been deleted.";
    }
}
