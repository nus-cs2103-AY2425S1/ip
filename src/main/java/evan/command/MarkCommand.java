package evan.command;

import evan.exception.NoSuchTaskException;
import evan.service.TaskList;

/**
 * Represents a command that the chatbot can execute to mark a task as complete.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Instantiates a MarkCommand object.
     *
     * @param taskNumber Task number of the task to be marked as complete.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws NoSuchTaskException {
        taskList.markTaskAsDone(taskNumber);
        return "Alright, I've marked task (" + taskNumber + ") as completed.";
    }
}
