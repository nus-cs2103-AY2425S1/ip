package evan.command;

import evan.exception.NoSuchTaskException;
import evan.service.TaskList;

/**
 * Represents a command that the chatbot can execute to mark a task as uncompleted.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Instantiates an UnmarkCommand object.
     *
     * @param taskNumber Task number of the task to be marked as uncompleted.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws NoSuchTaskException {
        taskList.markTaskAsUndone(taskNumber);
        return "Alright, I've marked task (" + taskNumber + ") as uncompleted.";
    }
}
