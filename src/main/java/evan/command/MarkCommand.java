package evan.command;

import evan.exception.NoSuchTaskException;
import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        taskList.markTaskAsDone(taskNumber);
        ui.showSuccess("Alright, I've marked task (" + taskNumber + ") as completed.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
